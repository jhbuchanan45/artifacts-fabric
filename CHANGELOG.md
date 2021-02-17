# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]
### Added
- Vampiric Glove
- Helium Flamingo
- Translations
  - Chinese
  - Pirate Speak

### Changed
- Slightly reduced Mimic knockback resistance
- Reduced digging claws mining level
- Slightly reduced digging claws mining speed

### Fixed
- Everlasting foods consumed by other mods' autofeeders
- The wrong texture for claws with slim hands was used

## [2.3.0] - 2020-01-25
### Added
- Add Umbrella to Origin's shields tag
- When the umbrella is held up (non-blocking), the player no longer gains effects from being rained on like extinguishing fire or Conduit Power
- Modify Origin's `exposed_to_sun` condition the umbrella is held up

## [2.2.1] - 2020-01-23
### Fixed
- Dedicated server fails to start due to mixin not finding client-side only class

## [2.2.0] - 2021-01-23
### Added
- Config option to disable extra hurt sounds for Kitty Slippers and Bunny Hoppers
- Dev: automated builds and releases

### Changed
- Tweaked loot tables
- Updated French translations

### Fixed
- Dev: use new fabric networking api
- Dev: refactor/reformat code

## 2.1.0 - 2020-12-31
### Added
- Spanish translations

### Fixed
- Eternal foods get consumed when fed to wolves/dogs
- A dedicated server disconnect when using the cloud in a bottle in a boat

## 2.0.2 - 2020-12-02
### Fixed
- Crash when using the pendants

## 2.0.1 - 2020-11-29
## Fixed
- Server side crash when toggling artifact effects
- Whoopee Cushion effect always active

## 2.0.0 - 2020-11-29
### Added
- Whoopee Cushion artifact
- Cloud in a Bottle artifact
- Disable/enable Artifact effects by shift-right clicking (cosmetic-only)

### Changed
- Tweaked equip sounds
- Rebalanced loot tables

### Fixes
- Mimics no longer spawn on peaceful
- Fixed a log warning

## 1.1.1 - 2020-11-21
### Fixed
- Attempt to fix a bug related to the thorn pendant and shulkers

## 1.1.0 - 2020-11-15
### Added
- Status effect cards now tell you which artifact they're from

### Changed
- Rebalanced loot tables
- Improved config (previous configs reset)
- Mimics now use the chest texture from resource packs

### Fixes
- The Umbrella does not negate fall damage
- Many tooltips go off the screen, often making them unreadable
- The right-click equip sound plays when equipping from the inventory and for all nearby players
- Crash when playing with Tic-TACS
- Unexpected behavior when holding two Umbrellas
- Sometimes Running Shoes do not immediately speed you up, making the FoV increase look weird

## 1.0.4 - 2020-11-09
### Fixes
- Incompatibility crash due to bug in Mixin library

## 1.0.3 - 2020-11-07
### Changes
- Update Russian translations

### Fixes
- Universal attractor only works on dropped items

## 1.0.2 - 2020-11-04
### Fixes
- Crash when using the Universal Attractor on a server

## 1.0.1 - 2020-11-03
### Changes
- Don't include Trinkets in jar file

### Fixes
- Several small hotfixes

## 1.0.0 - 2020-11-02
### Added
- Initial release

[Unreleased]: https://github.com/florensie/artifacts-fabric/compare/v2.3.0...HEAD
[2.3.0]: https://github.com/florensie/artifacts-fabric/compare/v2.2.1...v2.3.0
[2.2.1]: https://github.com/florensie/artifacts-fabric/compare/v2.2.0...v2.2.1
[2.2.0]: https://github.com/florensie/artifacts-fabric/releases/tag/v2.2.0
