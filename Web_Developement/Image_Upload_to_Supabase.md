# Image Storage Utilities

## Overview:

This file provides a set of functions for handling image files in a web application using Supabase Storage. It supports uploading, compressing, optimizing, deleting, and replacing images, as well as fetching image metadata. The code leverages the `sharp` library for image processing and follows best practices for file validation and error handling.

---

## Requirements:

- Node.js environment (for running server-side code)
- Supabase client library configured and exported from "@/supabase"
- Utility functions for API responses from "@/lib/utils/res-utils"
- `sharp` library for image processing (`npm install sharp`)
- Supabase project with a configured Storage bucket

---

## Parts (Code, Purpose, Explanation):

---

```js
"use server";

import supabase from "@/supabase";
import { ApiResponse, resServerError, resSuccess } from "@/lib/utils/res-utils";
import sharp from "sharp";
```
**Purpose:**  
Set up the environment for server-side code and import required dependencies.

**Explanation:**  
- `"use server";` signals this code is meant to run on the server.
- Imports the Supabase client for interacting with cloud storage.
- Imports API response helpers for standardizing success and error messages.
- Imports `sharp`, which is a powerful image processing library.

---

```js
/**
 * Configuration for image processing
 */
const IMAGE_CONFIG = {
  maxSize: 5 * 1024 * 1024, // 5MB max file size
  maxWidth: 2048, // Max width for resizing
  maxHeight: 2048, // Max height for resizing
  quality: 85, // JPEG/WebP quality
  formats: {
    jpeg: { quality: 85 },
    webp: { quality: 85 },
    png: { compressionLevel: 8 },
  },
};
```
**Purpose:**  
Defines configuration settings for handling images.

**Explanation:**  
- Sets limits on file size (5MB) and image dimensions (max 2048x2048 pixels).
- Specifies compression quality for different formats (JPEG, WebP, PNG).
- These settings are used to ensure images are optimized before uploading.

---

```js
/**
 * Supported image MIME types
 */
const SUPPORTED_IMAGE_TYPES = [
  "image/jpeg",
  "image/jpg",
  "image/png",
  "image/webp",
  "image/gif",
];
```
**Purpose:**  
Lists the allowed image types for upload.

**Explanation:**  
- Helps validate file types before processing.
- Prevents unsupported formats from being uploaded.

---

```js
/**
 * Convert File to Buffer for processing
 */
async function fileToBuffer(file: File): Promise<Buffer> {
  const arrayBuffer = await file.arrayBuffer();
  return Buffer.from(arrayBuffer);
}
```
**Purpose:**  
Converts a browser `File` object to a Node.js `Buffer` for further processing.

**Explanation:**  
- Images received from clients are `File` objects, but libraries like `sharp` require `Buffer`.
- This function handles the conversion asynchronously.

---

```js
/**
 * Compress and optimize image using sharp
 */
async function compressImage(
  buffer: Buffer,
  mimeType: string
): Promise<Buffer> {
  let image = sharp(buffer);

  // Get image metadata
  const metadata = await image.metadata();

  // Resize if image is too large
  if (
    metadata.width &&
    metadata.height &&
    (metadata.width > IMAGE_CONFIG.maxWidth ||
      metadata.height > IMAGE_CONFIG.maxHeight)
  ) {
    image = image.resize(IMAGE_CONFIG.maxWidth, IMAGE_CONFIG.maxHeight, {
      fit: "inside",
      withoutEnlargement: true,
    });
  }

  // Compress based on format
  if (mimeType === "image/jpeg" || mimeType === "image/jpg") {
    return await image
      .jpeg(IMAGE_CONFIG.formats.jpeg)
      .toBuffer();
  } else if (mimeType === "image/png") {
    return await image
      .png(IMAGE_CONFIG.formats.png)
      .toBuffer();
  } else if (mimeType === "image/webp") {
    return await image
      .webp(IMAGE_CONFIG.formats.webp)
      .toBuffer();
  }

  // Return original buffer for unsupported formats (like GIF)
  return buffer;
}
```
**Purpose:**  
Resizes and compresses images according to their format before uploading.

**Explanation:**  
- Uses `sharp` to inspect image metadata and resize if dimensions exceed the max allowed.
- Applies format-specific compression settings for JPEG, PNG, and WebP.
- GIFs and other unsupported formats are returned as-is (no compression).

---

```js
/**
 * Generate a unique filename
 */
function generateFileName(originalName: string): string {
  const timestamp = Date.now();
  const randomStr = Math.random().toString(36).substring(2, 15);
  const extension = originalName.split(".").pop() || "jpg";
  return `${timestamp}-${randomStr}.${extension}`;
}
```
**Purpose:**  
Creates a unique filename for each uploaded image to avoid collisions.

**Explanation:**  
- Combines a timestamp and a random string with the original file extension.
- Ensures every image has a unique identifier in storage.

---

```js
/**
 * Upload image to Supabase Storage
 * @param file - File object to upload
 * @param bucket - Supabase storage bucket name
 * @param folder - Optional folder path within the bucket
 * @returns ApiResponse with public URL of uploaded image
 */
export async function uploadImageAction(
  file: File,
  bucket: string,
  folder?: string
): Promise<ApiResponse<string>> {
  try {
    // Validate file type
    if (!SUPPORTED_IMAGE_TYPES.includes(file.type)) {
      return resServerError(
        `Unsupported file type. Supported types: ${SUPPORTED_IMAGE_TYPES.join(", ")}`
      );
    }

    // Validate file size (before compression)
    if (file.size > IMAGE_CONFIG.maxSize) {
      return resServerError(
        `File size exceeds ${IMAGE_CONFIG.maxSize / (1024 * 1024)}MB limit`
      );
    }

    // Convert file to buffer
    let buffer = await fileToBuffer(file);

    // Compress image if it's not a GIF
    if (file.type !== "image/gif") {
      buffer = await compressImage(buffer, file.type);
    }

    // Generate unique filename
    const fileName = generateFileName(file.name);
    const filePath = folder ? `${folder}/${fileName}` : fileName;

    // Upload to Supabase
    const { data, error } = await supabase.storage
      .from(bucket)
      .upload(filePath, buffer, {
        contentType: file.type,
        cacheControl: "3600",
        upsert: false,
      });

    if (error) {
      console.error("Supabase upload error:", error);
      return resServerError(`Failed to upload image: ${error.message}`);
    }

    // Get public URL
    const {
      data: { publicUrl },
    } = supabase.storage.from(bucket).getPublicUrl(data.path);

    return resSuccess(publicUrl);
  } catch (error) {
    console.error("Image upload error:", error);
    return resServerError(
      `Failed to upload image: ${error instanceof Error ? error.message : "Unknown error"}`
    );
  }
}
```
**Purpose:**  
Handles the complete process of validating, compressing, and uploading a single image to Supabase Storage.

**Explanation:**  
- Validates file type and size.
- Converts file to buffer and compresses if needed.
- Generates a unique filename and uploads to the specified bucket and folder.
- Returns the public URL if successful, or error details otherwise.

---

```js
/**
 * Upload multiple images
 * @param files - Array of File objects
 * @param bucket - Supabase storage bucket name
 * @param folder - Optional folder path within the bucket
 * @returns ApiResponse with array of public URLs
 */
export async function uploadMultipleImagesAction(
  files: File[],
  bucket: string,
  folder?: string
): Promise<ApiResponse<string[]>> {
  try {
    const uploadPromises = files.map((file) =>
      uploadImageAction(file, bucket, folder)
    );
    const results = await Promise.all(uploadPromises);

    // Check if any upload failed
    const failedUploads = results.filter((r: ApiResponse<string>) => !r.success);
    if (failedUploads.length > 0) {
      return resServerError(
        `${failedUploads.length} image(s) failed to upload`
      );
    }

    // Extract URLs from successful uploads
    const urls = results.map((r: ApiResponse<string>) => r.data as string);
    return resSuccess(urls);
  } catch (error) {
    console.error("Multiple images upload error:", error);
    return resServerError("Failed to upload images");
  }
}
```
**Purpose:**  
Allows uploading multiple images at once.

**Explanation:**  
- Uses `uploadImageAction` for each file in parallel.
- Collects the results and checks for any failures.
- Returns an array of public URLs or an error if any upload failed.

---

```js
/**
 * Delete image from Supabase Storage
 * @param url - Public URL of the image to delete
 * @param bucket - Supabase storage bucket name
 * @returns ApiResponse
 */
export async function deleteImageAction(
  url: string,
  bucket: string
): Promise<ApiResponse> {
  try {
    // Extract file path from public URL
    const urlObj = new URL(url);
    const pathParts = urlObj.pathname.split(`/storage/v1/object/public/${bucket}/`);
    
    if (pathParts.length < 2) {
      return resServerError("Invalid image URL");
    }

    const filePath = pathParts[1];

    // Delete from Supabase
    const { error } = await supabase.storage.from(bucket).remove([filePath]);

    if (error) {
      console.error("Supabase delete error:", error);
      return resServerError(`Failed to delete image: ${error.message}`);
    }

    return resSuccess("Image deleted successfully");
  } catch (error) {
    console.error("Image deletion error:", error);
    return resServerError(
      `Failed to delete image: ${error instanceof Error ? error.message : "Unknown error"}`
    );
  }
}
```
**Purpose:**  
Deletes a single image from Supabase Storage.

**Explanation:**  
- Extracts the file path from the public URL.
- Calls Supabase's remove method to delete the image.
- Handles errors and returns appropriate responses.

---

```js
/**
 * Delete multiple images from Supabase Storage
 * @param urls - Array of public URLs to delete
 * @param bucket - Supabase storage bucket name
 * @returns ApiResponse
 */
export async function deleteMultipleImagesAction(
  urls: string[],
  bucket: string
): Promise<ApiResponse> {
  try {
    const deletePromises = urls.map((url) => deleteImageAction(url, bucket));
    const results = await Promise.all(deletePromises);

    // Check if any deletion failed
    const failedDeletions = results.filter((r: ApiResponse) => !r.success);
    if (failedDeletions.length > 0) {
      return resServerError(
        `${failedDeletions.length} image(s) failed to delete`
      );
    }

    return resSuccess("All images deleted successfully");
  } catch (error) {
    console.error("Multiple images deletion error:", error);
    return resServerError("Failed to delete images");
  }
}
```
**Purpose:**  
Allows deleting multiple images at once.

**Explanation:**  
- Uses `deleteImageAction` for each URL in parallel.
- Collects the results and checks for failures.
- Returns success only if all deletions succeeded.

---

```js
/**
 * Replace an existing image (delete old, upload new)
 * @param oldUrl - URL of the image to replace
 * @param newFile - New file to upload
 * @param bucket - Supabase storage bucket name
 * @param folder - Optional folder path within the bucket
 * @returns ApiResponse with new public URL
 */
export async function replaceImageAction(
  oldUrl: string | null | undefined,
  newFile: File,
  bucket: string,
  folder?: string
): Promise<ApiResponse<string>> {
  try {
    // Upload new image first
    const uploadResult = await uploadImageAction(newFile, bucket, folder);

    if (!uploadResult.success) {
      return uploadResult;
    }

    // Delete old image if it exists
    if (oldUrl) {
      await deleteImageAction(oldUrl, bucket);
      // We don't fail the operation if deletion fails
    }

    return uploadResult;
  } catch (error) {
    console.error("Image replacement error:", error);
    return resServerError("Failed to replace image");
  }
}
```
**Purpose:**  
Replaces an old image with a new one (uploads new, deletes old).

**Explanation:**  
- Uploads the new image first and checks for success.
- Deletes the old image if a URL is provided.
- If deletion fails, it does not block the new upload.

---

```js
/**
 * Get image info from URL
 * @param url - Public URL of the image
 * @param bucket - Supabase storage bucket name
 * @returns Image metadata if exists
 */
export async function getImageInfoAction(
  url: string,
  bucket: string
): Promise<ApiResponse> {
  try {
    // Extract file path from public URL
    const urlObj = new URL(url);
    const pathParts = urlObj.pathname.split(`/storage/v1/object/public/${bucket}/`);
    
    if (pathParts.length < 2) {
      return resServerError("Invalid image URL");
    }

    const filePath = pathParts[1];

    // Get file info from Supabase (if available in your Supabase version)
    const { data, error } = await supabase.storage
      .from(bucket)
      .list(filePath.split("/").slice(0, -1).join("/"));

    if (error) {
      return resServerError("Failed to get image info");
    }

    const fileName = filePath.split("/").pop();
    const fileInfo = data?.find((f) => f.name === fileName);

    if (!fileInfo) {
      return resServerError("Image not found");
    }

    return resSuccess(fileInfo);
  } catch (error) {
    console.error("Get image info error:", error);
    return resServerError("Failed to get image info");
  }
}
```
**Purpose:**  
Fetches metadata about an image stored in Supabase.

**Explanation:**  
- Extracts the file path from the image's public URL.
- Lists files in the folder and finds the matching file by name.
- Returns file information or an error if not found.

---

## Summary

This utility file provides robust and easy-to-use functions for integrating image uploads, deletions, replacements, and metadata retrieval into any web application that uses Supabase Storage and Node.js. Each part is modular, well-documented, and designed to handle errors gracefully, making it simple for developers of any skill level to maintain or extend the code in the future.
