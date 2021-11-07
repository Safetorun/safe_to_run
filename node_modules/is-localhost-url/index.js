'use strict'

const REGEX_LOCALHOST = /^https?:\/\/(localhost|127(?:\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}|::1)/

module.exports = url => REGEX_LOCALHOST.test(url)
// module.exports.regex = REGEX_LOCALHOST
