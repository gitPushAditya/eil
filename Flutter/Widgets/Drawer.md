# Drawer

```dart
class DrawerWidget extends StatefulWidget {
  const DrawerWidget({super.key});

  @override
  State<DrawerWidget> createState() => _DrawerWidgetState();
}

class _DrawerWidgetState extends State<DrawerWidget> {
  @override
  Widget build(BuildContext context) {
    final ColorScheme appTheme = Theme.of(context).colorScheme;
    return Drawer(
      backgroundColor: appTheme.secondaryContainer,
      child: ListView(
        padding: const EdgeInsets.only(top: 20),
        children: [
          Container(
            padding: const EdgeInsets.only(
              top: 40,
              bottom: 40,
              left: 16,
            ),
            decoration: BoxDecoration(color: appTheme.primaryContainer),
            child: Row(
              crossAxisAlignment: CrossAxisAlignment.center,
              children: [
                CircleAvatar(
                  backgroundColor: appTheme.primaryContainer,
                  radius: 40,
                  child: Image.asset("assets/logo/logo_circular.png"),
                ),
                const SizedBox(
                  width: 16,
                ),
                Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    CustomText.h3(
                      "Simply Do",
                      color: appTheme.primary,
                      fontWeight: FontWeight.w700,
                    ),
                    CustomText.sub1(
                      "Premium User",
                      color: appTheme.primary,
                    )
                  ],
                ),
              ],
            ),
          ),
          const SizedBox(
            height: 8,
          ),
          ListTile(
            leading: const Icon(
              Icons.checklist,
              size: 28,
            ),
            title: CustomText.h6(
              "My Tasks",
            ),
            onTap: () {
              // TODO: Change Page
            },
          ),
          ListTile(
            leading: const Icon(
              Icons.format_list_bulleted,
              size: 28,
            ),
            title: CustomText.h6(
              "Timeline",
            ),
            onTap: () {
              // TODO: Change Page
            },
          ),
          ListTile(
            leading: const Icon(
              Icons.bar_chart,
              size: 28,
            ),
            title: CustomText.h6(
              "Analytics",
            ),
            onTap: () {
              // TODO: Change Page
            },
          ),
          ListTile(
            leading: const Icon(
              Icons.manage_accounts,
              size: 28,
            ),
            title: CustomText.h6(
              "Account",
            ),
            onTap: () {
              // TODO: Change Page
            },
          ),
          const Divider(),
          ListTile(
            iconColor: HighlightColors.highlightGolden,
            leading: const Icon(
              Icons.workspace_premium,
              size: 28,
            ),
            title: CustomText.h6(
              "Premium",
              color: HighlightColors.highlightGolden,
            ),
            onTap: () {
              // TODO: Change Page
            },
          ),
          ListTile(
            leading: const Icon(
              Icons.settings,
              size: 28,
            ),
            title: CustomText.h6(
              "Preference",
            ),
            onTap: () {
              // TODO: Change Page
            },
          ),
          const Divider(),
          ListTile(
            leading: const Icon(
              Icons.airplay,
              size: 28,
            ),
            title: CustomText.h6(
              "Tutorials",
            ),
            onTap: () {
              // TODO: Change Page
            },
          ),
          ListTile(
            leading: const Icon(
              Icons.alternate_email,
              size: 28,
            ),
            title: CustomText.h6(
              "Contact Us",
            ),
            onTap: () {
              // TODO: Change Page
            },
          ),
          ListTile(
            leading: const Icon(
              Icons.bug_report,
              size: 28,
            ),
            title: CustomText.h6(
              "Report a Bug",
            ),
            onTap: () {
              // TODO: Change Page
            },
          ),
          const Divider(),
        ],
      ),
    );
  }
}
```
