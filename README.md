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
### createSrc
create src and child dirs
```bash
$ gradle createSrc

create dir: src/main/java
create dir: src/main/resources
create dir: src/test/java
create dir: src/test/resources
```

### createModule
create a new module
```bash
$ gradle createModule --name foo

create dir: foo
create dir: foo/src/main/java
create dir: foo/src/main/resources
create dir: foo/src/test/java
create dir: foo/src/test/resources
create file: foo/build.gradle
```

if you use Idea, in order to make Idea **know a new module had been added**.

edit `foo/build.gradle` to add `id 'idea'` in `plugins`, like this:
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

## Communication
QQ群：536890082