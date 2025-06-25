# Packager

[![Maven Central](https://img.shields.io/maven-central/v/dev.redcrew/packager)](https://search.maven.org/artifact/dev.redcrew/packager)  
![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)

Packager is a lightweight Java library that lets you build, populate, and write Minecraft resource packs entirely in code. No more copying folders – simply declare textures, models, and JSON declarations, then export a ready-to-use `.zip` or folder.

----------

## Table of Contents

-   [Features](#features)

-   [Installation](#installation)
    
-   [Usage](#usage)
    
    -   [Building a Resource Pack](#building-a-resource-pack)
        
    -   [Writing to Disk](#writing-to-disk)
        
-   [API Overview](#api-overview)

-   [License](#license)
    


## Features

-   **Fluent Builder API** for constructing packs
    
-   Add textures (PNG) and models (JSON) by code
    
-   Automatic pack metadata (`pack.mcmeta`) generation
    
-   Support for newest Minecraft resourcepack format
    
-   Write to directory or ZIP archive

    


## Installation

### Maven

```xml
<dependency>
  <groupId>dev.redcrew</groupId>
  <artifactId>packager</artifactId>
  <version>1.0.0</version>
</dependency>

```

### Gradle (Kotlin)

```kotlin
implementation("dev.redcrew:packager:1.0.0")
```

## Usage

### Building a Resource Pack

```java
public static void main(String[] args) throws IOException {

        ItemModel catchnet = new ItemModelReader(new Location(Path.MODELS.extend(Path.ITEM)), "catchnet")
                .read(Objects.requireNonNull(Demo.class.getResourceAsStream("qitem_catchnet.json")));

        ResourcePack pack = new ResourcePack.Builder("Test Resource Pack", MinecraftVersion.v1_21_5)
                .setDescription("A useless Description")
                //Adding a custom Apple Texture
                .addTexture(new Texture(Path.TEXTURES.extend(Path.ITEM),
                        "apple",
                        Objects.requireNonNull(Demo.class.getResourceAsStream("apple.png"))))
                //Adding a Catchnet as Model
                .addTexture(new Texture(Path.TEXTURES.extend(Path.ITEM),
                        "catchnet",
                        Objects.requireNonNull(Demo.class.getResourceAsStream("qitem_catchnet.png"))))
                .addModel(catchnet)
                .addDeclaration(new BasicDeclaration(catchnet))
                //Build
                .build();

        ResourcePackWriter writer = new ResourcePackWriter(pack);
        writer.writeToDirectory(Paths.get("H:/Workspace/Intellij/Packager/Demo"), true);
        System.out.println("Done");
    }
```

### Writing to Disk

```java
// After building your ResourcePack instance...
ResourcePackWriter writer = new ResourcePackWriter(pack);

// Write as directory (true = overwrite if exists)
writer.writeToDirectory(Paths.get("output/PackagerDemo"), true);

// Or write as ZIP archive
writer.writeToZip(Paths.get("output/PackagerDemo.zip"), true);

```

## API Overview

### `ResourcePack.Builder`

-   **Constructor**: `Builder(String packName, MinecraftVersion version)`
    
-   `.setDescription(String description)`
    
-   `.addTexture(Texture texture)`
    
-   `.addModel(ItemModel model)`
    
-   `.addDeclaration(Declaration decl)`
    
-   `.build()` → `ResourcePack`
    

### `Texture`

-   **Constructor**: `Texture(Location path, String name, InputStream pngStream)`
    

### `ItemModelReader`

-   Reads a JSON model from classpath or file
    
-   **Constructor**: `ItemModelReader(Location path, String name)`
    
-   `.read(InputStream jsonStream)` → `ItemModel`
    

### `BasicDeclaration`

-   Convenience implementation of `Declaration` for item models
    

### `ResourcePackWriter`

-   **Constructor**: `ResourcePackWriter(ResourcePack pack)`
    
-   `.writeToDirectory(Path destDir, boolean overwrite)`
    
-   `.writeToZip(Path zipPath, boolean overwrite)`
    

For full Javadoc, see [the documentation site](https://docs.redcrew.dev/packager).

## License

This project is licensed under the **MIT License**. See LICENSE for full text.
