# is-url-http

![Last version](https://img.shields.io/github/tag/Kikobeats/is-url-http.svg?style=flat-square)
[![Coverage Status](https://img.shields.io/coveralls/Kikobeats/is-url-http.svg?style=flat-square)](https://coveralls.io/github/Kikobeats/is-url-http)
[![NPM Status](https://img.shields.io/npm/dm/is-url-http.svg?style=flat-square)](https://www.npmjs.org/package/is-url-http)

> Check if an URL is a valid HTTP URL.

## Install

```bash
$ npm install is-url-http --save
```

## Usage

```js
const isUrlHttp = require('is-url-http')

isUrlHttp('https://kikobeats.com') // ==> true
isUrlHttp('https://kikobeats.com') // ==> true
isUrlHttp('mailto://kiko@beats.com') // ==> false
isUrlHttp('callto:192.168.103.77+type=ip') // ==> false
```

If you need to run the package in a browser environment, you can save some bytes using the lightweight version:

```js
const isUrlHttp = require('is-url-http/lightweight')

isUrlHttp('https://kikobeats.com') // ==> true
isUrlHttp('https://kikobeats.com') // ==> true
isUrlHttp('mailto://kiko@beats.com') // ==> false
isUrlHttp('callto:192.168.103.77+type=ip') // ==> false
```

## License

**is-url-http** © [Kiko Beats](https://kikobeats.com), released under the [MIT](https://github.com/Kikobeats/is-url-http/blob/master/LICENSE.md) License.<br>
Authored and maintained by Kiko Beats with help from [contributors](https://github.com/Kikobeats/is-url-http/contributors).

> [kikobeats.com](https://kikobeats.com) · GitHub [Kiko Beats](https://github.com/Kikobeats) · Twitter [@Kikobeats](https://twitter.com/Kikobeats)
