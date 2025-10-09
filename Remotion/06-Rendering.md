# Rendering Your Video in Remotion

Learn how to turn your Remotion project into a real video file, GIF, or image!  
Here’s everything you need to know, step by step.

---

## 1. Ways to Render Your Video

### Remotion Studio (The Easy Way)

- Click the **Render** button in the Remotion Studio.
- Pick your settings, confirm, and your video will be created!

### Remotion Studio Deployment

- You can deploy Remotion Studio to a server, so your team can make videos from anywhere.  
  (See the "Deploy the Remotion Studio" guide.)

### Command Line (CLI)

- Run this in your project folder:
  ```bash
  npx remotion render HelloWorld
  ```
- Replace `HelloWorld` with your composition ID.
- You can also leave out the ID and pick from a list:
  ```bash
  npx remotion render
  ```

### Server-Side Rendering (SSR)

- Remotion has APIs to render videos in Node.js code.  
  (See the server-side rendering API docs.)

### AWS Lambda, Google Cloud Run, GitHub Actions

- Render videos in the cloud (for automation, scaling, or team workflows).

---

## 2. Render Variants

- **Audio-only:** Export just the audio, not the video.
- **Image Sequence:** Export a series of images (one for each frame).
- **Still Images:** Export a single image (thumbnail or poster frame).

---

## 3. Encoding and Quality Settings

Remotion uses FFmpeg under the hood — you have lots of options!

### Codec Choices

| Codec   | File Extension   | Size      | Speed   | Browser Compatibility | Hardware Acceleration | Notes |
|---------|------------------|-----------|---------|----------------------|----------------------|-------|
| H.264   | .mp4, .mov, .mkv | Medium    | Fast    | Very Good            | Yes (macOS)          | Default |
| H.265   | .mp4, .hevc      | Medium    | Fast    | Poor                 | Yes (macOS)          |        |
| VP8     | .webm            | Small     | Slow    | Okay                 | No                   |        |
| VP9     | .webm            | Very Small| Very Slow| Okay                | No                   |        |
| ProRes  | .mov             | Large     | Fast    | None                 | Yes (macOS)          | For editing |

- Choose codec using CLI flag (`--codec=vp8`) or in your config file.
- Hardware acceleration available on macOS for ProRes, H.264, H.265.

### Controlling Quality: CRF

- **CRF (Constant Rate Factor):** Lower = higher quality & bigger file.
- Each codec has its own range. Example for H.264: 1 (best) to 51 (worst), default 18.
- Set with `--crf=23` (CLI) or `Config.setCrf(23)` (config file).
- **Note:** If you enable hardware acceleration, you cannot set CRF.

### Controlling Quality: Bitrate

- Set video/audio bitrate for more control:
  - In Studio: Set in Render Dialog
  - CLI: `--video-bitrate=8M --audio-bitrate=128k`
  - In APIs: `videoBitrate` and `audioBitrate` options

### ProRes Profile

- For ProRes codec, set profile with `--prores-profile=4444` for alpha support.

---

## 4. Exporting Different Formats

- **Audio only:** Use mp3, wav, or aac as codec.
- **GIFs:** Use `--codec=gif` to export as GIF.
- **Still Images:** Use the `<Still />` component and `npx remotion still` CLI command.

**Render a still:**
```bash
npx remotion still my-comp out.png
```
- Set format with `--image-format=png|jpeg|webp|pdf`
- Choose frame with `--frame=42`

---

## 5. Output Scaling (Higher Resolution)

- Want to render your video in 4K instead of Full HD? Use output scaling!
- CLI: `--scale=2`
- Allowed values: 0.5 (half size) up to 16 (max 256x more pixels).

**What gets upscaled?**
- Text, SVG, images (if high-res)
- **Not upscaled:** Videos, canvas, WebGL

---

## 6. Transparent Videos

- Chrome/Firefox can play WebM videos with transparency (alpha channel).
- To export a transparent video:
  - Set PNG image format
  - Use VP8/VP9 codec and yuva420p pixel format
  - Example:
    ```bash
    npx remotion render --image-format=png --pixel-format=yuva420p --codec=vp8 my-video video-transparent.webm
    ```
- For ProRes with alpha (for video editors), use:
  ```bash
  npx remotion render --image-format=png --pixel-format=yuva444p10le --codec=prores --prores-profile=4444
  ```

---

## 7. Rendering GIFs

- Use `--codec=gif` to make a GIF.
- Lower frame rate for smaller GIFs: `--every-nth-frame=2`
- Control loop count: `--number-of-gif-loops=0` (no repeat), `--number-of-gif-loops=1` (loops once), etc.

- Transparent GIFs: Must use PNG as image format.

---

## 8. Creating Overlays (ProRes with Alpha)

- Export as transparent ProRes file for use in Final Cut Pro, Premiere, DaVinci Resolve.

**Config example:**
```ts
import { Config } from "@remotion/cli/config";
Config.setVideoImageFormat("png");
Config.setPixelFormat("yuva444p10le");
Config.setCodec("prores");
Config.setProResProfile("4444");
```

---

## 9. Batch Rendering: Multiple Videos from a Dataset

- Loop over a JSON dataset and render each entry as a separate video.

**Example:**
```js
import { renderMedia, selectComposition } from '@remotion/renderer';
import { data } from './dataset';

for (const entry of data) {
  const composition = await selectComposition({
    serveUrl: bundleLocation,
    id: compositionId,
    inputProps: entry,
  });
  await renderMedia({
    composition,
    serveUrl: bundleLocation,
    codec: 'h264',
    outputLocation: `out/${entry.name}.mp4`,
    inputProps: entry,
  });
}
```

---

## 10. Artifacts: Emitting Extra Files

- Use `<Artifact />` to emit subtitles, thumbnails, or credits as files during render.

**Example:**
```tsx
import { Artifact, useCurrentFrame } from 'remotion';
export const MyComp = () => {
  const frame = useCurrentFrame();
  return <>{frame === 0 ? <Artifact filename="captions.srt" content={generateSubtitles()} /> : null}</>;
};
```
- Artifacts are saved in the output folder.

---

## 11. Setting Video Metadata

- Add info like title, artist, comment, genre, etc. using the `--metadata` flag or in APIs.

**Example:**
```bash
npx remotion render MyComp --metadata='{"title":"My Video","artist":"Me"}'
```

---

## 12. Hardware Acceleration (Advanced)

- On macOS, Remotion supports hardware-accelerated encoding for ProRes, H.264, H.265.
- Enable with `--hardware-acceleration if-possible` or in config.
- Results in faster renders, but bigger files (use video bitrate to control size).

---

## 13. Output Formats Summary

- **Videos:** H.264 (.mp4), H.265 (.hevc), VP8/VP9 (.webm), ProRes (.mov), GIF
- **Audio-only:** .mp3, .wav, .aac
- **Images:** .png, .jpeg, .webp, .pdf

---

## Summary

- Render your video using Remotion Studio, CLI, APIs, or cloud services.
- Choose the right codec, quality, and format for your needs.
- Batch and automate renders from datasets.
- Emit extra files and set metadata.
- Make transparent videos, overlays, GIFs, and more!
- Control resolution, quality, and hardware acceleration.

---