---
id: why 
title: Why Safe to Run? 
slug: /
---
:::caution

No solution to tamper detection is foolproof, if someone is able to decompile your application and push it onto an
unsuspecting device, it is possible remove the functionality of safe to run.

This just makes it that much harder...

:::

## Motivation

Safe to run has been developed in order to simplify development of secure android applications. In particular the goal
of the project is to provide a simple, configurable way for app developers to define when an app should or should not
run and a simple way of calling that check.

Following are a list of things that Safe to run can help protect against and the checks that can help with them

|                   | Hardening against de & recompilation | Harden against reverse engineers and pentesters | Harden against insecure devices |
|-------------------|--------------------------------------|-------------------------------------------------|---------------------------------|
| Signature check   | [x]                                  | [x]                                             |                                 |
| Root detection    |                                      | [x]                                             | [x]                             |
| OS Check          |                                      |                                                 | [x]                             |
| Blacklisting apps |                                      | [x]                                             |                                 |
| Debug check       |                                      | [x]                                             |                                 |
| Install origin    |                                      | [x]                                             |                                 |
| Emulator check    | [x]                                  | [x]                                             |                                 |

## Checks

Safe to run consists of a number of 'checks' which are detailed in the documentation. The purpose of these checks is to
ensure that the app is 'safe to run' i.e. it meets the pre-conditions you have set.



