# Embedding Videos into Remotion (Explained Simply)

Remotion lets you add videos to your own video projects.  
Here’s how to use and control them!

---

## 1. How to Embed a Video

Use the `<OffthreadVideo />` component to put a video in your Remotion project.

**Online video example:**
```tsx
import { OffthreadVideo } from 'remotion';
export const MyComp = () => (
  <OffthreadVideo src="https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4" />
);
```

**Local video example:**  
Put your video (like `video.mp4`) in the `public` folder.
```tsx
import { OffthreadVideo, staticFile } from 'remotion';
export const MyComp = () => <OffthreadVideo src={staticFile('video.mp4')} />;
```

---

## 2. Trimming Videos

You can skip the start or end of a video:

- **Skip first 2 seconds (@ 30 fps):**
  ```tsx
  <OffthreadVideo src={staticFile('video.mp4')} trimBefore={60} />
  ```

- **Trim start and end:**
  ```tsx
  <OffthreadVideo src={staticFile('video.mp4')} trimBefore={60} trimAfter={120} />
  ```

---

## 3. Delay Video Playback

Use `<Sequence from={...}>` to start a video later in your timeline.

```tsx
import { OffthreadVideo, staticFile, Sequence } from 'remotion';
export const MyComp = () => (
  <Sequence from={60}>
    <OffthreadVideo src={staticFile('video.mp4')} />
  </Sequence>
);
```

---

## 4. Size and Position

Just use CSS!

```tsx
<OffthreadVideo
  src={staticFile('video.mp4')}
  style={{
    width: 640,
    height: 360,
    position: 'absolute',
    top: 100,
    left: 100,
  }}
/>
```

---

## 5. Volume & Muted

Set the volume or mute the video:

```tsx
<OffthreadVideo src={staticFile('video.mp4')} volume={0.5} />
<OffthreadVideo src={staticFile('video.mp4')} muted />
```

---

## 6. Speed (Playback Rate)

Play the video faster or slower:

```tsx
<OffthreadVideo src={staticFile('video.mp4')} playbackRate={2} />
```
> Only works if the speed stays the same.

---

## 7. Match Composition Duration to Video

If you want your composition to be as long as your video, use `calculateMetadata()` to set video length automatically.

**Example:**
```tsx
import {CalculateMetadataFunction} from 'remotion';
import {parseMedia} from '@remotion/media-parser';

export const calculateMetadata: CalculateMetadataFunction<{src: string}> = async ({props}) => {
  const {slowDurationInSeconds, dimensions} = await parseMedia({
    src: props.src,
    fields: {slowDurationInSeconds: true, dimensions: true},
  });

  const fps = 30;
  return {
    durationInFrames: Math.floor(slowDurationInSeconds * fps),
    fps,
    width: dimensions.width,
    height: dimensions.height,
  };
};
```

Register your composition in `Root.tsx`:
```tsx
<Composition
  id="MyComp"
  component={MyComp}
  defaultProps={{ src: 'your-video.mp4' }}
  calculateMetadata={calculateMetadata}
/>
```

---

## 8. Play Multiple Videos in Sequence

Use `<Series>` and `<OffthreadVideo>` to play several videos one after another.

**Basic example:**
```tsx
import { OffthreadVideo, Series } from 'remotion';
export const VideosInSequence = ({videos}) => (
  <Series>
    {videos.map((vid) => (
      <Series.Sequence key={vid.src} durationInFrames={vid.durationInFrames}>
        <OffthreadVideo src={vid.src} />
      </Series.Sequence>
    ))}
  </Series>
);
```

**Calculate metadata to sum durations:**
```tsx
export const calculateMetadata = async ({props}) => {
  const fps = 30;
  const videos = await Promise.all(
    props.videos.map(async (video) => {
      const {slowDurationInSeconds} = await parseMedia({
        src: video.src,
        fields: {slowDurationInSeconds: true},
      });
      return {
        durationInFrames: Math.floor(slowDurationInSeconds * fps),
        src: video.src,
      };
    })
  );
  const totalDuration = videos.reduce((acc, v) => acc + v.durationInFrames, 0);
  return {fps, durationInFrames: totalDuration, props: {videos}};
};
```

---

## 9. Transparent Videos

- **With alpha channel:**
  ```tsx
  <OffthreadVideo src={staticFile('transparent.webm')} transparent />
  ```
- **Without alpha channel (black background):**
  ```tsx
  <OffthreadVideo src={staticFile('nottransparent.mp4')} style={{mixBlendMode: 'screen'}} />
  ```

---

## 10. Video Manipulation (Canvas Effects)

You can draw video frames to a `<canvas>` and apply custom effects.

**Basic grayscale example:**
```tsx
import { OffthreadVideo } from 'remotion';
export const VideoOnCanvas = () => {
  const video = useRef(null);
  const canvas = useRef(null);
  const {width, height} = useVideoConfig();
  const onVideoFrame = useCallback((frame) => {
    if (!canvas.current) return;
    const context = canvas.current.getContext('2d');
    if (!context) return;
    context.filter = 'grayscale(100%)';
    context.drawImage(frame, 0, 0, width, height);
  }, [height, width]);
  return (
    <AbsoluteFill>
      <AbsoluteFill>
        <OffthreadVideo style={{opacity: 0}} onVideoFrame={onVideoFrame} src="video.mp4" />
      </AbsoluteFill>
      <AbsoluteFill>
        <canvas ref={canvas} width={width} height={height} />
      </AbsoluteFill>
    </AbsoluteFill>
  );
};
```

---

## 11. Changing Video Speed Over Time

To change speed dynamically, you need to remap the frames!  
Don't just interpolate `playbackRate` directly.

**Accelerated video example:**
```tsx
const remapSpeed = (frame, speedFn) => {
  let framesPassed = 0;
  for (let i = 0; i <= frame; i++) {
    framesPassed += speedFn(i);
  }
  return framesPassed;
};

export const AcceleratedVideo = () => {
  const frame = useCurrentFrame();
  const speedFunction = (f) => interpolate(f, [0, 500], [1, 5]);
  const remappedFrame = remapSpeed(frame, speedFunction);
  return (
    <Sequence from={frame}>
      <OffthreadVideo
        trimBefore={Math.round(remappedFrame)}
        playbackRate={speedFunction(frame)}
        src="your-video.mp4#disable"
      />
    </Sequence>
  );
};
```

---

## 12. Jump Cutting

To skip parts of a video (jump cut), use a custom logic with `trimBefore` and `trimAfter`.

**Example:**
```tsx
const SAMPLE_SECTIONS = [
  {trimBefore: 0, trimAfter: 150},
  {trimBefore: 210, trimAfter: 300},
  {trimBefore: 390, trimAfter: 540},
];
export const JumpCuts = ({sections}) => {
  const frame = useCurrentFrame();
  const trimBefore = useMemo(() => {
    let summedUpDurations = 0;
    for (const section of sections) {
      summedUpDurations += section.trimAfter - section.trimBefore;
      if (summedUpDurations > frame) {
        return section.trimAfter - summedUpDurations;
      }
    }
    return null;
  }, [frame, sections]);
  if (trimBefore === null) return null;
  return (
    <OffthreadVideo
      pauseWhenBuffering
      trimBefore={trimBefore}
      src={`${staticFile('time.mp4')}#t=0,`}
    />
  );
};
```

---

## 13. Freeze Portions of a Sequence

Freeze portions of your video and resume afterwards:

```tsx
import { Freeze, Sequence, useCurrentFrame } from 'remotion';
export const FreezePortion = () => {
  const FREEZES = [
    { frame: 0, durationInFrames: 25 },
    { frame: 30, durationInFrames: 50 },
  ];
  // ...logic to freeze based on frame...
  return (
    <Freeze frame={freezeFrame} active={isFreezing}>
      <Sequence layout="none" from={fromFrame}>
        {/* Your content */}
      </Sequence>
    </Freeze>
  );
};
```

---

## 14. HLS Streaming Support

Remotion does **not** natively support HLS (`.m3u8`) files for rendering.  
You can use [hls.js](https://github.com/video-dev/hls.js/) to preview HLS videos in Remotion Studio, but not for final render.

**Preview example:**
```tsx
import Hls from 'hls.js';
import { Video } from 'remotion';
const HlsVideo = ({src}) => {
  const videoRef = useRef(null);
  useEffect(() => {
    const hls = new Hls();
    hls.loadSource(src);
    hls.attachMedia(videoRef.current);
    return () => hls.destroy();
  }, [src]);
  return <Video ref={videoRef} src={src} />;
};
```

---

## 15. OffthreadVideo vs. Video

| Component         | Pros                                                                 | Cons                                                                  |
|-------------------|---------------------------------------------------------------------|-----------------------------------------------------------------------|
| **OffthreadVideo**| More videos at once, no flickers, more codecs, fast rendering      | Entire video must download before rendering, transparent videos slower |
| **Video**         | Can render before full download, easy to attach ref                | Fewer codecs, Chrome can throttle, duplicate frames if framerate differs |

**Tip:** Use `<OffthreadVideo />` for best results in most cases!

---

## Summary

- Use `<OffthreadVideo />` to embed videos.
- Control trim, delay, position, volume, speed, transparency, freeze, and jump cuts.
- Use canvas for custom effects.
- Use `<Series />` for sequencing multiple videos.
- `<OffthreadVideo />` is usually better than `<Video />`.
- HLS streams can be previewed but not rendered out.

---