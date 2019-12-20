# Changelog

All notable changes for the Core platform will be documented in this file.

Note that there is no changelog available for the initial release of the platform (2.0.0), you can find the release notes [here](https://github.com/xatkit-bot-platform/xatkit-core-platform/releases).

The changelog format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/), and this project adheres to [Semantic Versioning](https://semver.org/v2.0.0.html)

## Unreleased

### Added

- Action `StoreValue` allowing to store a *key/value* pair in the **global bot scope**. Values stored with this action can be accessed from any bot installation, session, or context.
- Action `GetValue` allowing to retrieve a *value* stored in the **global bot scope** with `StoreValue`. This action does not rely on the session to retrieve the value.
- `CronEventProvider`, a provider that generates periodic `CronTick` events that can be used to schedule actions in a bot (fix [#1](https://github.com/xatkit-bot-platform/xatkit-core-platform/issues/1)). The start date/time as well as the period can be specified in the Xatkit properties file using the keys `xatkit.core.cron.start_on` and `xatkit.core.cron.period`, respectively.

## [3.0.0] 2019-12-01

### Changed
- Action parameters and return are now statically typed. **This change breaks the public API**: execution models relying on the generic `Object` type for parameter and return now need to cast values to the expected type. (e.g. `ChatPlatform.Reply(message)` now requires that `message` is a `String`, this can be fixed with the following syntax `ChatPlatform.Reply(message as String)`).  

### Deleted

- Removed `Random` action, this action is not needed anymore with the Xbase integration, and can be replaced by `Math.random` in the execution model. **This change breaks the public API**: existing execution models relying on `CorePlatform.Random` should update to the new java-based syntax.

## [2.0.1] - 2019-10-10

### Changed

- Updated internal dependencies to the latest versions.

## [2.0.0] - 2019-08-20 

See the release notes [here](https://github.com/xatkit-bot-platform/xatkit-core-platform/releases).
