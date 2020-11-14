# Artifacts [![CurseForge](http://cf.way2muchnoise.eu/full_401236_downloads.svg)](https://www.curseforge.com/minecraft/mc-mods/artifacts-fabric)
Artifacts is a minecraft mod that adds various powerful items which can be found through exploration. 
More information can be found here:
https://www.curseforge.com/minecraft/mc-mods/artifacts-fabric

Tracked upstream commit: 67705597

## Translating
*This is a note for people who wish to contribute translations for this mod*

This mod has several long descriptions as tooltips for items.
Say you had this as a tooltip:
```
"item.artifacts.coolitem.tooltip": "Wow this item is SO cool, I could to write an entire paragraph about it here!"
```
In vanilla Minecraft this tooltip would start going of the screen, leaving it mostly unreadable.
Because of this, this mod allows splitting up tooltips like this:
```
"item.artifacts.coolitem.tooltip[0]": "Wow this item is SO cool,",
"item.artifacts.coolitem.tooltip[1]": "I could to write an entire",
"item.artifacts.coolitem.tooltip[2]": "paragraph about it here!",
```
All you have to do is add `[i]` to the translation key, where `i` is the line number starting at `0`.

The general rule for the length of a line in this mod, is to not exceed the length of the tooltip added by Trinkets.