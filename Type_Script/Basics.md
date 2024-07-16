# TypeScript Basics

## Intro

A programming language created to address shortcomings of JavaScript. Built on top of JS.

Two types of programming language - Statically Typed and Dynamically Typed

### Statically Typed

We know the type during coding and compiling.

eg. C++, C#, Java

If a variable is of type "int", it can only hold int value.

### Dynamically Typed

Type is not known during coding or compiling and is determined at runtime and can be changed.

eg. JS, Python, Ruby

_TypeScript is JS with type safety._

## Variables

Defining type of variable -

```ts
let name:string;
let age: number = 20;
let isStudent: boolean;
let hobbies: string[];  // Array of string
let role: [number, string] //  Tuples, no of elements and types are defined

// For Objects
type Person = {
    name: string;
    age?: number;
};

 // type can be replaced with interface

let person: Person = {
    name: "Aditya";
}

 // For array fo Objects

let personsArray: Person[];

 // For a variable to have two types
let year : number | string;

 // To have any value
let anyVariable: any;

 // or 

let anyVariable : unknown;  // recommended
```
  
## Functions 

Two ways: 

`let printName: Function;`  

Or better way - 

`let printName: (name: string) => void;` void returns undefined but can be replaced with `never` which doesn't return anything.

