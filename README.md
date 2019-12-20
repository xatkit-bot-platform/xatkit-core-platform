Xatkit Core Platform
=====

[![License Badge](https://img.shields.io/badge/license-EPL%202.0-brightgreen.svg)](https://opensource.org/licenses/EPL-2.0)
[![Build Status](https://travis-ci.com/xatkit-bot-platform/xatkit-core-platform.svg?branch=master)](https://travis-ci.com/xatkit-bot-platform/xatkit-core-platform)
[![Wiki Badge](https://img.shields.io/badge/doc-wiki-blue)](https://github.com/xatkit-bot-platform/xatkit-releases/wiki/Xatkit-Core-Platform)

Toolbox of generic actions that can be used in Xatkit execution models. This platform is bundled with the [Xatkit release](https://github.com/xatkit-bot-platform/xatkit-releases/releases).

## Providers

The Core platform defines the following provider:

| Provider          | Type  | Context Parameters | Description                                                  |
| ----------------- | ----- | ------------------ | ------------------------------------------------------------ |
| CronEventProvider | Event | -                  | Periodically generates *CronTick* events to schedule bot actions. The start date/time as well as the period can be specified in the Xatkit properties file. |

## Actions

| Action     | Parameters                                                   | Return                                                       | Return Type | Description                                                  |
| ---------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ----------- | ------------------------------------------------------------ |
| GetTime    | -                                                            | The current time (format: `hh:mm:ss`)                        | String      | Returns the current time formatted as a String.              |
| GetDate    | -                                                            | The current date (format: `dd/mm/yyyy`)                      | String      | Returns the current date formatted as a String.              |
| StoreValue | - `key` (**String**): the key associated to the value to store. <br/>- `value` (**Object**): the value to store | The stored value                                             | Object      | Store the provided *key/value* pair in the global bot scope. |
| GetValue   | - `key` (**String**): the key associated to the value to retrieve. <br/>- (*Optional*) `defaultValue` (**Object**): the value to return if the global bot scope does not contain the provided *key* | The retrieved value, or the `defaultValue` if the global bot scope does not contain the provided *key* | Object      | Retrieves the value associated to the provided *key* from the global bot scope. |

## Options

The Core platform supports the following configuration options

| Key                         | Values | Description                                                  | Constraint                                                   |
| --------------------------- | ------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| `xatkit.core.cron.start_on` | String | The date/time used to start the `CronEventProvider`  event generation. **Note**: this date must follow the ISO_DATE_TIME format (it should be parsable by `DateTimeFormatter#ISO_DATE_TIME`. | **Optional** (default is equal to `Instant.now()`)           |
| `xatkit.core.cron.period`   | Long   | The interval between two *CronTick* events (in **seconds**). | **Optional** (default `-1`, meaning that only one *CronTick* will be generated) |