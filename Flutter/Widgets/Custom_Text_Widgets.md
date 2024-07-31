# Custom Texts

```dart
class CustomText extends StatelessWidget {
  final String text;
  final double fontSize;
  final Color? color;
  final FontWeight? fontWeight;

  const CustomText({
    super.key,
    required this.text,
    this.fontSize = 16, // Default font size
    this.color,
    this.fontWeight,
  });

  factory CustomText.h1(String text,
      {double? fontSize, Color? color, FontWeight? fontWeight}) {
    return CustomText(
      text: text,
      fontSize: fontSize ?? 48,
      color: color,
      fontWeight: fontWeight ?? FontWeight.w300,
    );
  }

  factory CustomText.h2(String text,
      {double? fontSize, Color? color, FontWeight? fontWeight}) {
    return CustomText(
      text: text,
      fontSize: fontSize ?? 34,
      color: color,
      fontWeight: fontWeight ?? FontWeight.w300,
    );
  }

  factory CustomText.h3(String text,
      {double? fontSize, Color? color, FontWeight? fontWeight}) {
    return CustomText(
      text: text,
      fontSize: fontSize ?? 28,
      color: color,
      fontWeight: fontWeight ?? FontWeight.w400,
    );
  }

  factory CustomText.h4(String text,
      {double? fontSize, Color? color, FontWeight? fontWeight}) {
    return CustomText(
      text: text,
      fontSize: fontSize ?? 24,
      color: color,
      fontWeight: fontWeight ?? FontWeight.w400,
    );
  }

  factory CustomText.h5(String text,
      {double? fontSize, Color? color, FontWeight? fontWeight}) {
    return CustomText(
      text: text,
      fontSize: fontSize ?? 22,
      color: color,
      fontWeight: fontWeight ?? FontWeight.w400,
    );
  }

  factory CustomText.h6(String text,
      {double? fontSize, Color? color, FontWeight? fontWeight}) {
    return CustomText(
      text: text,
      fontSize: fontSize ?? 20,
      color: color,
      fontWeight: fontWeight ?? FontWeight.w500,
    );
  }

  factory CustomText.sub1(String text,
      {double? fontSize, Color? color, FontWeight? fontWeight}) {
    return CustomText(
      text: text,
      fontSize: fontSize ?? 18,
      color: color,
      fontWeight: fontWeight ?? FontWeight.w400,
    );
  }

  factory CustomText.sub2(String text,
      {double? fontSize, Color? color, FontWeight? fontWeight}) {
    return CustomText(
      text: text,
      fontSize: fontSize ?? 16,
      color: color,
      fontWeight: fontWeight ?? FontWeight.w500,
    );
  }

  factory CustomText.body1(String text,
      {double? fontSize, Color? color, FontWeight? fontWeight}) {
    return CustomText(
      text: text,
      fontSize: fontSize ?? 16,
      color: color,
      fontWeight: fontWeight ?? FontWeight.w400,
    );
  }

  factory CustomText.body2(String text,
      {double? fontSize, Color? color, FontWeight? fontWeight}) {
    return CustomText(
      text: text,
      fontSize: fontSize ?? 14,
      color: color,
      fontWeight: fontWeight ?? FontWeight.w400,
    );
  }

  factory CustomText.button(String text,
      {double? fontSize, Color? color, FontWeight? fontWeight}) {
    return CustomText(
      text: text.toUpperCase(),
      fontSize: fontSize ?? 14,
      color: color,
      fontWeight: fontWeight ?? FontWeight.w500,
    );
  }

  factory CustomText.caption(String text,
      {double? fontSize, Color? color, FontWeight? fontWeight}) {
    return CustomText(
      text: text,
      fontSize: fontSize ?? 12,
      color: color,
      fontWeight: fontWeight ?? FontWeight.w400,
    );
  }

  factory CustomText.outline(String text,
      {double? fontSize, Color? color, FontWeight? fontWeight}) {
    return CustomText(
      text: text,
      fontSize: fontSize ?? 10,
      color: color,
      fontWeight: fontWeight ?? FontWeight.w500,
    );
  }

  @override
  Widget build(BuildContext context) {
    Color appTheme = Theme.of(context).colorScheme.onPrimaryContainer;
    return Text(
      text,
      style: TextStyle(
        fontSize: fontSize,
        color: color ?? appTheme, // Use theme color if color is not provided
        fontWeight: fontWeight,
        fontFamily: "Roboto",
      ),
    );
  }
}

```
