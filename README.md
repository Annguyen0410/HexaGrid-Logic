# HexaGrid Logic MVP

A minimal working version of a hexagonal grid-based interaction screen for Android using Kotlin.

## Features

✅ **Hexagonal Grid Display**: Dynamic hexagonal grid rendered using Canvas  
✅ **Cell Interaction**: Touch detection on individual hex cells  
✅ **Visual Feedback**: Cells change color when tapped (gray → green)  
✅ **Reset Functionality**: Reset button returns all cells to default state  

## How to Use

1. **Tap hexagon cells**: Touch any hexagon to change its color from gray to green
2. **Toggle cells**: Tap a green cell to return it to gray
3. **Reset grid**: Press the "Reset" button to return all cells to their default gray color

## Technical Implementation

- **Custom View**: `HexagonGridView` extends Android's `View` class
- **Canvas Rendering**: Hexagons drawn using Android's Canvas API with Path objects
- **Touch Detection**: Point-in-hexagon collision detection for precise touch handling
- **Grid Layout**: 8 rows × 6 columns with alternating row offsets for proper hexagonal tessellation
- **Color Management**: Simple boolean state tracking for each hexagon cell

## Project Structure

```
app/src/main/java/com/pixelbitst/hexagridlogic_/
├── MainActivity.kt          # Main activity with reset button logic
└── HexagonGridView.kt      # Custom view for hexagonal grid rendering

app/src/main/res/layout/
└── activity_main.xml       # Layout with ScrollView, HexagonGridView, and Reset button
```

## Building the App

```bash
./gradlew assembleDebug
```

The app targets Android API 24+ and compiles with SDK 35.

## Next Steps (Beyond MVP)

This MVP provides a solid foundation for extending with:
- Animations and transitions
- Game logic and scoring
- Persistent state saving
- Multi-touch gestures
- Performance optimizations
- Different hexagon patterns or sizes
