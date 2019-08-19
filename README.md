Core Platform
=====

[![License Badge](https://img.shields.io/badge/license-EPL%202.0-brightgreen.svg)](https://opensource.org/licenses/EPL-2.0)
[![Build Status](https://travis-ci.com/xatkit-bot-platform/xatkit-core-platform.svg?branch=master)](https://travis-ci.com/xatkit-bot-platform/xatkit-core-platform)

Toolbox of generic actions that can be used in Xatkit execution models. This platform is bundled with the [Xatkit release](https://github.com/xatkit-bot-platform/xatkit-releases/releases).



## Providers

The core platform does not define any provider.

## Actions

| Action  | Parameters | Return                                  | Return Type | Description                                     |
| ------- | ---------- | --------------------------------------- | ----------- | ----------------------------------------------- |
| GetTime | -          | The current time (format: `hh:mm:ss`)   | String      | Returns the current time formatted as a String. |
| GetDate | -          | The current date (format: `dd/mm/yyyy`) | String      | Returns the current date formatted as a String. |
| Random | - `bound` (**Integer**): the bound of the number to generate (exclusive) | A random Integer between `0` (inclusive) and `bound` (exclusive) | Integer | Returns a random number each time it is called |

