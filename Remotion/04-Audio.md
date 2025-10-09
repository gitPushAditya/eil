# Importing and Using Audio in Remotion
Learn how to add music, sound effects, and voiceovers to your Remotion videos!

---

## 1. Adding Audio

### Local Audio

- Put your audio file (like `audio.mp3`) in the `public/` folder.
- Use Remotion's `<Audio />` component with `staticFile()`:

```tsx
import { AbsoluteFill, Audio, staticFile } from 'remotion';

export const MyComposition = () => (
  <AbsoluteFill>
    <Audio src={staticFile('audio.mp3')} />
  </AbsoluteFill>
);
```

### Remote Audio

- You can also use a direct URL:

```tsx
import { AbsoluteFill, Audio } from 'remotion';

export const MyComposition = () => (
  <AbsoluteFill>
    <Audio src="https://example.com/audio.mp3" />
  </AbsoluteFill>
);
```

---

## 2. Mixing Multiple Tracks

Just add more `<Audio />` tags to play multiple sounds at once.

---

## 3. Trimming Audio

- Use `trimBefore` and `trimAfter` to cut the start/end of the audio.

```tsx
import { AbsoluteFill, Audio, staticFile, useVideoConfig } from 'remotion';

export const MyComposition = () => {
  const { fps } = useVideoConfig();
  return (
    <AbsoluteFill>
      <Audio src={staticFile('audio.mp3')} trimBefore={2 * fps} trimAfter={4 * fps} />
    </AbsoluteFill>
  );
};
```
- This plays only the section from 2s to 4s.

---

## 4. Delaying Audio

- Use `<Sequence from={...}>` to start audio later.

```tsx
import { AbsoluteFill, Audio, Sequence, staticFile } from 'remotion';

export const MyComposition = () => (
  <AbsoluteFill>
    <Sequence from={100}>
      <Audio src={staticFile('audio.mp3')} />
    </Sequence>
  </AbsoluteFill>
);
```
- This starts audio after 100 frames.

---

## 5. Controlling Volume

- Set volume (0 = silent, 1 = full volume):

```tsx
<Audio src={staticFile('audio.mp3')} volume={0.5} />
```

- Change volume over time (fade in/out):

```tsx
<Audio
  src={staticFile('audio.mp3')}
  volume={f => interpolate(f, [0, 30], [0, 1], { extrapolateLeft: 'clamp' })}
/>
```
- Here, volume fades in for the first 1 second (if fps=30).

> **Note:** If volume changes, use a function for best results.

**Volume Limitations:**
- Can't set volume > 1 (unless you use `useWebAudioApi` and CORS audio).
- On iOS Safari, volume is always 1 unless you use `useWebAudioApi`.

---

## 6. Muting Audio

- Mute with the `muted` prop (can also change over time):

```tsx
<Audio src={staticFile('audio.mp3')} muted={frame >= 60 && frame <= 120} />
```
- Here, audio is muted between frames 60 and 120.

---

## 7. Playback Speed

- Use `playbackRate` (1 = normal, 2 = twice as fast, 0.5 = half speed):

```tsx
<Audio src={staticFile('audio.mp3')} playbackRate={2} />
```

---

## 8. Pitch Control

- Change pitch with `toneFrequency` (1 = normal, 0.5 = half pitch, 1.5 = 50% higher):

```tsx
<Audio src={staticFile('audio.mp3')} toneFrequency={0.8} />
```

---

## 9. Audio from Video

- Audio from `<Video />` and `<OffthreadVideo />` is included automatically.
- You can trim, delay, mute, speed up, and change volume, just like with `<Audio />`.

```tsx
<OffthreadVideo src={staticFile('video.mp4')} playbackRate={2} volume={0.5} />
```

---

## 10. Audio Visualization

- Remotion can visualize music (create audiograms, bar charts, waveforms).

**Bar Visualization Example:**
```tsx
import { useAudioData, visualizeAudio } from '@remotion/media-utils';
import { Audio, staticFile, useCurrentFrame, useVideoConfig } from 'remotion';

const music = staticFile('music.mp3');

export const MyComponent = () => {
  const frame = useCurrentFrame();
  const { width, height, fps } = useVideoConfig();
  const audioData = useAudioData(music);

  if (!audioData) return null;

  const visualization = visualizeAudio({
    fps,
    frame,
    audioData,
    numberOfSamples: 16,
  });

  return (
    <div>
      <Audio src={music} />
      {visualization.map((v) => (
        <div style={{ width: 1000 * v, height: 15, backgroundColor: 'blue' }} />
      ))}
    </div>
  );
};
```
- Each bar shows the strength of a frequency at the current frame.

---

## 11. Exporting Audio

- When you export your video, audio is included by default.

### Export Audio Only

**Command Line:**
```bash
npx remotion render src/index.ts my-comp out/audio.mp3
npx remotion render src/index.ts my-comp --codec=mp3
```

**Server-side:**
```js
await renderMedia({
  composition,
  serveUrl: bundleLocation,
  codec: 'mp3',
  outputLocation,
  inputProps,
});
```

**Lambda:**
```js
await renderMediaOnLambda({
  region: 'us-east-1',
  functionName: 'remotion-render-bds9aab',
  composition: 'MyVideo',
  serveUrl: 'https://remotionlambda.site.url',
  inputProps: {},
  codec: 'mp3',
  imageFormat: 'none',
});
```

### Exclude Audio

**Command Line:**
```bash
npx remotion render --muted
```
**Server-side:**
```js
await renderMedia({ ..., muted: true });
```
**Lambda:**
```js
await renderMediaOnLambda({ ..., muted: true });
```

---

## 12. Order of Audio Operations (v4.0.141+)

Remotion processes audio in a specific order:
1. **Trim** audio (`trimBefore`)
2. **Offset** audio (using `<Sequence from={...}>`)
3. **Stretch** audio (`playbackRate`)

**Example:**
- `<Audio trimBefore={45} />` = skips first 1.5s (at 30fps)
- `<Sequence from={30}>` = delays audio by 1s
- `<Audio playbackRate={2} />` = speeds up by 2x

So, the audio plays **from 1.5s to 3.5s** in the timeline, between frames 30 and 59, at double speed.

---

## Summary

- Use `<Audio />` for sound in Remotion.
- Control start, end, delay, volume, mute, speed, and pitch.
- Mix multiple tracks by adding more `<Audio />` tags.
- Visualize audio with Remotion APIs.
- Export audio only (mp3, aac, wav), or mute it.
- Audio is processed: trim → offset → stretch.

---