# Parameterized Videos in Remotion

Remotion lets you make videos that change based on data you give them.  
You can pass in text, numbers, colors, or even fetch info from an API!

---

## 1. What Are Parameterized Videos?

- Videos whose content, size, length, or frame rate can change based on the data ("props") you give them.
- Props are like settings you pass to your video, just like you pass props to a React component.

---

## 2. How Props Work in Remotion

### a. **Default Props**

- You set these when you register your video composition.
- They let you preview your video in Remotion Studio even if no data is given.

```tsx
type Props = { propOne: string; propTwo: number; }

export const MyComponent: React.FC<Props> = ({ propOne, propTwo }) => (
  <div>props: {propOne}, {propTwo}</div>
);

// In Root.tsx
<Composition
  id="my-video"
  width={1080}
  height={1080}
  fps={30}
  durationInFrames={30}
  component={MyComponent}
  defaultProps={{ propOne: 'Hi', propTwo: 10 }}
/>
```

### b. **Input Props**

- You can override default props when you render the video (from CLI, code, or UI).
- Input props are merged with default props: input props win if there's a conflict.

**CLI Example:**
```bash
npx remotion render my-video out/video.mp4 --props='{"propOne": "Hello", "propTwo": 42}'
```
**Server Example:**
```js
await renderMedia({
  composition,
  serveUrl,
  codec: 'h264',
  outputLocation,
  inputProps: { propOne: "Hello", propTwo: 42 },
});
```

### c. **calculateMetadata()**

- You can fetch data, calculate video duration, or change video size dynamically before rendering.
- This is where you can fetch from APIs and change the props or metadata!

**Example:**
```tsx
import { Composition } from "remotion";
export const Root = () => (
  <Composition
    id="MyComp"
    component={MyComp}
    durationInFrames={300}
    fps={30}
    width={1920}
    height={1080}
    defaultProps={{ id: "1", data: null }}
    calculateMetadata={async ({ props }) => {
      const data = await fetch(`https://example.com/api/${props.id}`);
      const json = await data.json();
      return { props: { ...props, data: json } };
    }}
  />
);
```

---

## 3. Defining Props Shape with TypeScript or Zod

- Use TypeScript types or Zod schemas to define what props your video expects.

**TypeScript Example:**
```tsx
type Props = { propOne: string; propTwo: number; }
```

**Zod Example:**
```tsx
import { z } from 'zod';
export const myCompSchema = z.object({
  propOne: z.string(),
  propTwo: z.string(),
});
```
- You can use the schema in your composition for type safety and visual prop editing in Remotion Studio.

---

## 4. Editing Props Visually in Remotion Studio

- If you add a Zod schema, Remotion Studio lets you edit props with sliders, pickers, etc.
- Press the sidebar icon or Cmd/Ctrl + J to open the props editor.
- You can also edit raw JSON for advanced control.

---

## 5. Fetching Data for Videos

### Using calculateMetadata

- Use `calculateMetadata()` to fetch API data before rendering.
- This way, data is fetched only once and shared across all render threads.

**Best Practice:**
- Make sure the API returns the same data every time (so your video doesn't flicker).
- Use the abortSignal to cancel stale requests.

**Example:**
```tsx
export const calculateMyCompMetadata = async ({ props, abortSignal }) => {
  const data = await fetch(`https://example.com/api/${props.id}`, { signal: abortSignal });
  const json = await data.json();
  return { props: { ...props, data: json } };
};
```

---

## 6. Dynamic Duration, Size, and FPS

- You can set `durationInFrames`, `width`, `height`, or `fps` dynamically in `calculateMetadata()`.

**Example:**
```tsx
export const calculateMyCompMetadata = ({ props }) => {
  const fps = 30;
  const durationInSeconds = props.durationInSeconds;
  return { durationInFrames: durationInSeconds * fps, fps };
};
```

---

## 7. Data Fetching During Render (Advanced)

If you need to fetch data during rendering, use `delayRender()` and `continueRender()`:

```tsx
import { useEffect, useState } from "react";
import { cancelRender, continueRender, delayRender } from "remotion";

export const MyComp = () => {
  const [data, setData] = useState(null);
  const [handle] = useState(() => delayRender());

  useEffect(() => {
    fetch("http://example.com/api")
      .then(res => res.json())
      .then(json => {
        setData(json);
        continueRender(handle);
      })
      .catch(err => cancelRender(err));
  }, [handle]);

  return <div>{data ? JSON.stringify(data) : null}</div>;
};
```

- Use this only if you need to fetch non-JSON data or use old Remotion versions.
- Don't fetch every frame (no dependency on `frame` in `useEffect`).

---

## 8. Using Props in Player

- You can use the same prop logic in `<Player />` components for websites.
- Fetch or calculate metadata, then pass to `<Player />`.

**Example:**
```tsx
import { Player } from '@remotion/player';
const [duration, setDuration] = useState(1);
useEffect(() => {
  parseMedia({ src: 'video.mp4', fields: { slowDurationInSeconds: true } })
    .then(({ slowDurationInSeconds }) => setDuration(Math.round(slowDurationInSeconds * 30)));
}, []);
return <Player component={VideoTesting} compositionWidth={1080} compositionHeight={1080} fps={30} durationInFrames={duration} />;
```

---

## 9. How Are Props Resolved?

1. **Default props:** Used if no other data given.
2. **Input props:** Passed at render time; override default props.
3. **calculateMetadata():** Can fetch/transform props and set metadata.
4. The final props are passed to your React component to make your video!

---

## 10. Tips and Best Practices

- Prefer using `calculateMetadata()` for data fetching and dynamic settings.
- Use Zod schemas for visual editing and validation.
- Avoid fetching data inside your component unless necessary.
- Make sure your data is the same for all render threads.
- Only use frame-dependent fetching if you know what you’re doing!

---

## Summary

- Remotion videos can be parameterized by passing props—just like React components!
- You can set default props, override them when rendering, and fetch data before rendering with `calculateMetadata()`.
- Use TypeScript or Zod schemas for safety and visual editing.
- Props can change what’s shown, as well as duration, size, and more.
- Make your videos dynamic, data-driven, and reusable!

---