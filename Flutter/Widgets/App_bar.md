# App Bar

```dart
class AppBarWidget extends StatelessWidget implements PreferredSizeWidget {
  final String headingText;

  const AppBarWidget({
    super.key,
    required this.headingText,
  });

  @override
  Widget build(BuildContext context) {
    final ColorScheme appTheme = Theme.of(context).colorScheme;
    return AppBar(
      title: CustomText.h4(headingText),
      backgroundColor: appTheme.tertiaryContainer,
      surfaceTintColor: appTheme.tertiaryContainer,
      elevation: 5,
      centerTitle: true,
      toolbarHeight: 65,
      leading: IconButton(
        icon: const Icon(
          Icons.menu,
          size: 30,
        ),
        onPressed: () {
          Scaffold.of(context).openDrawer();
        },
      ),
      actions: [
        IconButton(
          onPressed: () {
            // TODO: Take to premium page
          },
          icon: const Icon(
            Icons.account_circle_outlined,
            size: 30,
          ),
        )
      ],
    );
  }

  @override
  // TODO: implement preferredSize
  Size get preferredSize => const Size.fromHeight(65);
}
```