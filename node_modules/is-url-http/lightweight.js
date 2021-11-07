'use strict'

const URL = global.window ? window.URL : require('url').URL
const REGEX_HTTP_PROTOCOL = /^https?:\/\//i

module.exports = url => {
  try {
    return REGEX_HTTP_PROTOCOL.test(new URL(url).href)
  } catch (err) {
    return false
  }
}
