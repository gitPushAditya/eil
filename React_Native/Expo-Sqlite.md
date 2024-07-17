# Expo Sqlite

```
npx expo install expo-sqlite
```

Import the module -

```js
import * as SQLite from "expo-sqlite";
```  
## Basic CRUD Operations -

`const db = await SQLite.openDatabaseAsync('databaseName');`

### For Bulk Queries -

```js
await db.execAsync(`
PRAGMA journal_mode = WAL;
CREATE TABLE IF NOT EXIST test (id INTEGER PRIMARY KEY NOT NULL, value TEXT);
INSERT INTO test (value) VALUES ('test1');
INSERT INTO test (value) VALUES ('test2');
`);
```

### For Some Queries -

```js
const result = await db.runAsync(
  "INSERT INTO test (value, intValue) VALUES (?, ?)",
  "aaa",
  100
);
console.log(result.lastInsertRowId, result.changes);
await db.runAsync("UPDATE test SET intValue = ? WHERE value = ?", 999, "aaa"); // Binding unnamed parameters from variadic arguments
await db.runAsync("UPDATE test SET intValue = ? WHERE value = ?", [999, "aaa"]); // Binding unnamed parameters from array
await db.runAsync("DELETE FROM test WHERE value = $value", { $value: "aaa" }); // Binding named parameters from object
```

### For SIngle Row

```js
const firstRow = await db.getFirstAsync("SELECT * FROM test");
console.log(firstRow.id, firstRow.value, firstRow.intValue);
```
  
### To get all results as an Array of Objects

```js
const allRows = await db.getAllAsync("SELECT * FROM test");
for (const row of allRows) {
  console.log(row.id, row.value, row.intValue);
}
```
  
### To iterate SQLite query cursor

```js
for await (const row of db.getEachAsync("SELECT * FROM test")) {
  console.log(row.id, row.value, row.intValue);
}
```
