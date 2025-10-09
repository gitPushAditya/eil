# Remotion Basics Guide

A simple, step-by-step guide to help you understand Remotion and start making videos with code. 

---

## 1. Getting Started with Remotion

Remotion is a tool for creating videos using code (React).  
Here's how to start:

### Prerequisites

- Node.js v16+ **OR** Bun v1.0.3+ installed.
- Linux users: Needs Libc v2.35+. No Alpine/nixOS.

### Create Your First Project

1. Open your terminal.
2. Run:
   ```bash
   npx create-video@latest
   ```
3. Pick the **Hello World** template.
4. Open your project folder in your code editor.

### Start Remotion Studio

Run:
```bash
npm run dev
```
This opens Remotion Studio in your browser to preview and edit your video.

---

## 2. Remotion Fundamentals

### What Is Remotion?

- Remotion lets you make videos with React code.
- You control each frame and can animate anything!

### The Frame System

- Each frame is like a blank canvas.
- `useCurrentFrame()` gives you the current frame number.

**Example: Show the frame number**
```tsx
import { AbsoluteFill, useCurrentFrame } from "remotion";
export const MyComposition = () => {
  const frame = useCurrentFrame();
  return (
    <AbsoluteFill style={{ justifyContent: "center", alignItems: "center", fontSize: 100, backgroundColor: "white" }}>
      The current frame is {frame}.
    </AbsoluteFill>
  );
};
```

### Video Properties

- **width** (pixels)
- **height** (pixels)
- **durationInFrames** (total frames)
- **fps** (frames per second)

Get these using `useVideoConfig()`:
```tsx
import { useVideoConfig } from "remotion";
const { fps, durationInFrames, width, height } = useVideoConfig();
```

### Compositions

- A composition = React component + video settings.
- Register it in `src/Root.tsx` with `<Composition />`.

```tsx
import { Composition } from "remotion";
import { MyComposition } from "./MyComposition";
export const RemotionRoot = () => (
  <Composition
    id="MyComposition"
    durationInFrames={150}
    fps={30}
    width={1920}
    height={1080}
    component={MyComposition}
  />
);
```

---

## 3. Previewing Your Video

- Run `npm run dev` to open Remotion Studio.
- Pick a composition to preview.
- Use controls: play, pause, scrub, frame step, zoom.
- Code changes update live!

---

## 4. Animating Properties

### What Is Animation?

Changing properties (like color, size, opacity) frame by frame to make things move or appear smoothly.

### Fade-In Animation Example

```tsx
import { AbsoluteFill, useCurrentFrame } from "remotion";
export const FadeIn = () => {
  const frame = useCurrentFrame();
  const opacity = Math.min(1, frame / 60); // 0 to 1 over 60 frames
  return (
    <AbsoluteFill style={{ justifyContent: "center", alignItems: "center", backgroundColor: "white", fontSize: 80 }}>
      <div style={{ opacity }}>Hello World!</div>
    </AbsoluteFill>
  );
};
```

### Using `interpolate`

```tsx
import { interpolate, useCurrentFrame } from "remotion";
const frame = useCurrentFrame();
const opacity = interpolate(frame, [0, 60], [0, 1], { extrapolateRight: "clamp" });
```

### Spring Animations

```tsx
import { spring, useCurrentFrame, useVideoConfig } from "remotion";
export const MyVideo = () => {
  const frame = useCurrentFrame();
  const { fps } = useVideoConfig();
  const scale = spring({ fps, frame });
  return (
    <div style={{ flex: 1, textAlign: "center", fontSize: "7em" }}>
      <div style={{ transform: `scale(${scale})` }}>Hello World!</div>
    </div>
  );
};
```

### Important

- Always use `useCurrentFrame()` for frame-driven animation.
- Don't use regular CSS transitions!

---

## 5. Making Components Reusable

### Why Reuse?

- Write once, use everywhere.
- Keeps code neat.

### Example: Reusable Title Component

```tsx
import { interpolate, useCurrentFrame } from "remotion";
const Title = ({ title }) => {
  const frame = useCurrentFrame();
  const opacity = interpolate(frame, [0, 20], [0, 1], { extrapolateRight: "clamp" });
  return <div style={{ opacity, textAlign: "center", fontSize: "7em" }}>{title}</div>;
};
```

Use in your video:
```tsx
import { AbsoluteFill } from "remotion";
export const MyVideo = () => (
  <AbsoluteFill>
    <Title title="Hello World" />
  </AbsoluteFill>
);
```

### Showing Multiple Titles: `<Sequence>`

```tsx
import { AbsoluteFill, Sequence } from "remotion";
export const MyVideo = () => (
  <AbsoluteFill>
    <Sequence durationInFrames={40}>
      <Title title="Hello" />
    </Sequence>
    <Sequence from={40}>
      <Title title="World" />
    </Sequence>
  </AbsoluteFill>
);
```

**Notes:**  
- `useCurrentFrame()` starts at 0 for each sequence.
- `<Sequence>` is absolutely positioned by default (items can overlap).
- Use `layout="none"` to stack like normal HTML.

---

## 6. Summary

- Remotion = code-driven videos using React.
- Start with `npx create-video@latest`, pick "Hello World".
- Use `useCurrentFrame()` for animation.
- Register compositions in `src/Root.tsx`.
- Preview with `npm run dev`.
- Make reusable components and control timing with `<Sequence>`.
- Animate properties using math, `interpolate`, and `spring`.
- Always drive animations by frame number.

---

## Note to Self

- Add video settings in `Root.tsx` when creating compositions.
- Use `useVideoConfig()` inside components to access video info.