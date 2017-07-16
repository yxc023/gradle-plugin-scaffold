# gradle-plugin-scaffold
a gradle plugin to generate src folder, modules ...

## import
add line `id 'com.yangxiaochen.scaffold' version 'lastest version'` to plugins, like this:
```bash
plugins {
    id 'java'
    id 'idea'
    id "com.yangxiaochen.scaffold" version "1.0.2"
}
```

## usage
```bash
$ gradle createSrc
```

```bash
$ gradle createModule --name foo

create dir: foo
create dir: foo/src/main/java
create dir: foo/src/main/resources
create dir: foo/src/test/java
create dir: foo/src/test/resources
create file: foo/build.gradle
```

if you use Idea

then edit `foo/build.gradle` to add `id 'idea'` in `plugins`, like this:
```bash
plugins {
    id 'java'
    id 'idea'
}
```
then
```bash
$ gradle idea
```


## sugguest wanted
please write issue