# Remotion Designing

A simple guide to help you learn Remotion step by step.  

---

## 1. Getting Started with Remotion

Remotion lets you create videos using code (React).  
Here’s how to start:

### Prerequisites

- Node.js v16+ **OR** Bun v1.0.3+ installed.
- Linux: Needs Libc v2.35+. No Alpine/nixOS.

### Creating Your First Project

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

- Make videos with React code.
- You control each frame and animate anything!

### The Frame System

- Each frame is a blank canvas.
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

## 3. Importing Assets

### What are assets?

Images, videos, audio, etc. Add them to your video!

### How to Import Assets

#### 1. Use the `public/` Folder

Put assets (images, videos, audio) in a folder called `public`.

```
my-video/
├─ public/
│  ├─ logo.png
│  ├─ tune.mp3
│  ├─ vid.webm
```

#### 2. Import Images

Use Remotion’s `<Img />` and `staticFile()`:

```tsx
import { Img, staticFile } from "remotion";
export const MyComp = () => <Img src={staticFile("logo.png")} />;
```

You can also use online images:

```tsx
<Img src="https://picsum.photos/id/237/200/300" />
```

#### 3. Image Sequences (Frame Animations)

```tsx
import { Img, staticFile, useCurrentFrame } from "remotion";
export const MyComp = () => {
  const frame = useCurrentFrame();
  return <Img src={staticFile(`frame${frame}.png`)} />;
};
```

#### 4. Importing Videos

Best way: `<OffthreadVideo />` or `<Video />` and `staticFile()`.

```tsx
import { OffthreadVideo, staticFile } from "remotion";
export const MyComp = () => <OffthreadVideo src={staticFile("vid.webm")} />;
```

Online videos work too:

```tsx
<OffthreadVideo src="https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4" />
```

#### 5. Importing Audio

```tsx
import { Audio, staticFile } from "remotion";
export const MyComp = () => <Audio src={staticFile("tune.mp3")} />;
```

Online audio:

```tsx
<Audio src="https://file-examples.com/storage/fe48a63c5264cbd519788b3/2017/11/file_example_MP3_700KB.mp3" />
```

#### 6. Importing CSS

Put your CSS file in `src/` and import:

```tsx
import "./style.css";
```

#### 7. Direct File Imports

You can use `import` or `require()` for images, videos, audio, and fonts (but prefer `staticFile()`).

```tsx
import logo from "./logo.png";
<Img src={logo} />
```

#### 8. Assets Must Be Inside the Project

Remotion can’t access files outside your project folder.  
Always put assets in `public/`.

#### 9. Use Remotion Components

Always use Remotion’s `<Img />`, `<OffthreadVideo />`/`<Video />`, `<Audio />`.

**Why?**
- Ensures assets are loaded before each frame.
- Keeps everything in sync with the timeline.

---

## 4. Layers

### What are Layers?

Layer = stack things on top of each other (like Photoshop).

### How to Make Layers

Use `<AbsoluteFill>`:

```tsx
import { AbsoluteFill, Img, staticFile } from "remotion";
export const MyComp = () => (
  <AbsoluteFill>
    <AbsoluteFill>
      <Img src={staticFile('bg.png')} />
    </AbsoluteFill>
    <AbsoluteFill>
      <h1>This text appears on top of the video!</h1>
    </AbsoluteFill>
  </AbsoluteFill>
);
```

Order in code = order in layers (lower in code = on top).

### Showing Layers for a Limited Time

Use `<Sequence>`:

```tsx
import { AbsoluteFill, Img, staticFile, Sequence } from "remotion";
export const MyComp = () => (
  <AbsoluteFill>
    <Sequence>
      <Img src={staticFile('bg.png')} />
    </Sequence>
    <Sequence from={60} durationInFrames={40}>
      <h1>This text appears after 60 frames!</h1>
    </Sequence>
  </AbsoluteFill>
);
```

- `<Sequence>` is absolutely positioned by default.
- Layer order: lower = higher.

---

## 5. The 5 Basic Transforms

### What Are Transforms?

Transforms let you change how things look or move (size, position, rotation, etc).

### The 5 Types

1. **Opacity**
   ```tsx
   <div style={{ opacity: 0.5 }} />
   ```
2. **Scale**
   ```tsx
   <div style={{ transform: "scale(0.5)" }} />
   ```
3. **Skew**
   ```tsx
   <div style={{ transform: "skew(20deg)" }} />
   ```
4. **Translate**
   ```tsx
   <div style={{ transform: "translateX(100px)" }} />
   ```
5. **Rotate**
   ```tsx
   <div style={{ transform: "rotate(45deg)" }} />
   ```

**Combine transforms:**  
```tsx
<div style={{ transform: "translateX(100px) scale(2)" }} />
```
Order matters!

### More Ways to Transform

- Animate height/width, border-radius, box-shadow, color, SVG paths, font weight/slant, gradient stops, filters, etc.

---

## 6. Animating Properties

### What Is Animation?

Change properties (like opacity or scale) over time, frame by frame.

### Fade-In Example

```tsx
import { AbsoluteFill, useCurrentFrame } from "remotion";
export const FadeIn = () => {
  const frame = useCurrentFrame();
  const opacity = Math.min(1, frame / 60);
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

**Important:** Always animate with `useCurrentFrame()`.  
Don't use regular CSS transitions.

---

## 7. Making Components Reusable

### Why?

- Write once, use everywhere. Keeps code neat!

### Example: Title Component

```tsx
import { interpolate, useCurrentFrame } from "remotion";
const Title = ({ title }) => {
  const frame = useCurrentFrame();
  const opacity = interpolate(frame, [0, 20], [0, 1], { extrapolateRight: "clamp" });
  return <div style={{ opacity, textAlign: "center", fontSize: "7em" }}>{title}</div>;
};
```

Use it in your video:

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

- `useCurrentFrame()` starts at 0 for each sequence.
- `<Sequence>` is absolutely positioned by default.

---

## 8. Transitions

### What are Transitions?

Animations between scenes or elements (slide, fade, flip, etc).

### How to Use `<TransitionSeries>`

```tsx
import { linearTiming, TransitionSeries } from "@remotion/transitions";
import { slide } from "@remotion/transitions/slide";
const BasicTransition = () => (
  <TransitionSeries>
    <TransitionSeries.Sequence durationInFrames={40}>
      <Letter color="#0b84f3">A</Letter>
    </TransitionSeries.Sequence>
    <TransitionSeries.Transition
      presentation={slide()}
      timing={linearTiming({ durationInFrames: 30 })}
    />
    <TransitionSeries.Sequence durationInFrames={60}>
      <Letter color="pink">B</Letter>
    </TransitionSeries.Sequence>
  </TransitionSeries>
);
```

**Duration math:**  
Total animation time = (scene1) + (scene2) - (transition)  
Example: 40 + 60 - 30 = 70 frames

### Types of Presentations

- `fade()`, `slide()`, `wipe()`, `flip()`, `clockWipe()`, `iris()`, `cube()`, `none()`
- You can create your own effects!

**Timing:**  
- `springTiming()`, `linearTiming()`, or custom

**Rules:**  
1. Transition can't be longer than previous/next sequence.
2. Don't put two transitions next to each other.
3. Must have a sequence before/after each transition.

**Audio transitions:** You can add sound effects to transitions.

---

## 9. Using Fonts

### Why Use Custom Fonts?

Fonts change how your text looks!

### 1. Google Fonts (Easy Way)

```tsx
import { loadFont } from "@remotion/google-fonts/TitanOne";
const { fontFamily } = loadFont();
const GoogleFontsComp = () => <div style={{ fontFamily }}>Hello, Google Fonts</div>;
```

### 2. Google Fonts (CSS Way)

`font.css`
```css
@import url("https://fonts.googleapis.com/css2?family=Bangers");
```
In your component:
```tsx
import "./font.css";
const MyComp = () => <div style={{ fontFamily: "Bangers" }}>Hello</div>;
```

### 3. Local Fonts (@remotion/fonts)

Put font in `public/`, then:

```tsx
import { loadFont } from "@remotion/fonts";
import { staticFile } from "remotion";
const fontFamily = "Inter";
loadFont({
  family: fontFamily,
  url: staticFile("Inter-Regular.woff2"),
  weight: "500",
}).then(() => { console.log("Font loaded!"); });
```
Use inside your component:
```tsx
<div style={{ fontFamily }}>Some text</div>
```

### 4. Local Fonts (Manual)

```tsx
import { continueRender, delayRender, staticFile } from "remotion";
const waitForFont = delayRender();
const font = new FontFace(`Bangers`, `url('${staticFile("bangers.woff2")}') format('woff2')`);
font.load().then(() => {
  document.fonts.add(font);
  continueRender(waitForFont);
});
```

**Tip:** If TypeScript errors, update `@types/web`.

---

## 10. Measuring DOM Nodes

### Why Measure?

Sometimes you need the size/position of an element (like a `<div>`).

### Why is this tricky in Remotion?

Remotion puts your video inside a `<div>` that uses `scale()` transform—so measurements are scaled.

### Solution: Use `useCurrentScale()` (v4.0.111+)

```tsx
import { useRef, useState, useEffect } from "react";
import { useCurrentScale } from "remotion";
export const MyComponent = () => {
  const ref = useRef(null);
  const [dimensions, setDimensions] = useState(null);
  const scale = useCurrentScale();
  useEffect(() => {
    if (!ref.current) return;
    const rect = ref.current.getBoundingClientRect();
    setDimensions({
      correctedHeight: rect.height / scale,
      correctedWidth: rect.width / scale,
    });
  }, [scale]);
  return (
    <div>
      <div ref={ref}>Hello World!</div>
      {/* Use dimensions.correctedHeight and correctedWidth */}
    </div>
  );
};
```

**Older versions:**  
Measure a hidden `<div>` with known width and use both sizes to get the scale.

---

## 11. Using Randomness

### Why is Randomness Tricky?

`Math.random()` gives different results in different threads—your video can break!

### Correct way: Use Remotion's `random()` API

```tsx
import { random } from "remotion";
const randomValues = new Array(10).fill(true).map((a, i) => ({
  x: random(`x-${i}`),
  y: random(`y-${i}`),
}));
```

**Want true random?**  
Use `random(null)`.

**Exception:**  
True randomness is safe in `calculateMetadata()`.

---

## 12. Noise Visualization

### What is Noise?

Smooth, random movement (like wiggly lines or floating dots).

### Use `@remotion/noise` for cool effects:

```tsx
import { noise3D } from "@remotion/noise";
import { interpolate, useCurrentFrame, useVideoConfig } from "remotion";
const NoiseComp = ({ speed, circleRadius, maxOffset }) => {
  const frame = useCurrentFrame();
  const { height, width } = useVideoConfig();
  return (
    <svg width={width} height={height}>
      {new Array(15).fill(0).map((_, i) =>
        new Array(10).fill(0).map((__, j) => {
          const x = i * ((width + 100) / 15);
          const y = j * ((height + 100) / 10);
          const px = i / 15;
          const py = j / 10;
          const dx = noise3D("x", px, py, frame * speed) * maxOffset;
          const dy = noise3D("y", px, py, frame * speed) * maxOffset;
          const opacity = interpolate(
            noise3D("opacity", i, j, frame * speed),
            [-1, 1],
            [0, 1]
          );
          return (
            <circle
              key={`${i}-${j}`}
              cx={x + dx}
              cy={y + dy}
              r={circleRadius}
              fill="gray"
              opacity={opacity}
            />
          );
        })
      )}
    </svg>
  );
};
```

**Props:**  
- `speed` = how fast dots move
- `circleRadius` = size of each dot
- `maxOffset` = how far each dot can wiggle

---

## 13. Animation Math

### What is Animation Math?

Mix numbers together for cool effects!  
Add, subtract, or multiply animation values for natural motion.

### Example: Enter and Exit Animation

```tsx
import { AbsoluteFill, spring, useCurrentFrame, useVideoConfig } from "remotion";
export const AnimationMath = () => {
  const frame = useCurrentFrame();
  const { fps, durationInFrames } = useVideoConfig();
  const enter = spring({ fps, frame, config: { damping: 200 } });
  const exit = spring({
    fps,
    config: { damping: 200 },
    durationInFrames: 20,
    delay: durationInFrames - 20,
    frame,
  });
  const scale = enter - exit;
  return (
    <AbsoluteFill style={{ justifyContent: "center", alignItems: "center", backgroundColor: "white" }}>
      <div
        style={{
          height: 100,
          width: 100,
          backgroundColor: "#4290f5",
          borderRadius: 20,
          transform: `scale(${scale})`,
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          fontSize: 50,
          color: "white",
        }}
      >
        {frame}
      </div>
    </AbsoluteFill>
  );
};
```

- At the start, `enter` grows from 0 to 1 (box grows).
- At the end, `exit` grows from 0 to 1 (box shrinks).
- `scale = enter - exit` gives you smooth appear/disappear.

---

## 14. Summary

- Start with `npx create-video@latest`, pick "Hello World".
- Use `useCurrentFrame()` for animation.
- Register compositions in `src/Root.tsx`.
- Preview with `npm run dev`.
- Make reusable components and control timing with `<Sequence>`, `<TransitionSeries>`.
- Animate properties with math, `interpolate`, and `spring`.
- Use Remotion’s special components for assets, layers, fonts, randomness, and noise.
- Always drive animations by frame number!

---
