'use strict'

const test = require('ava')

const isLocalhostUrl = require('..')

test('true', t => {
  t.true(isLocalhostUrl('http://localhost:3000'))
  t.true(isLocalhostUrl('https://localhost:1337'))
  t.true(isLocalhostUrl('https://127.0.0.1:8080'))
  t.true(isLocalhostUrl('http://127.0.0.1:80'))
  t.true(isLocalhostUrl('http://127.0.1.0:80'))
  t.true(isLocalhostUrl('http://::1:8001'))
  t.true(isLocalhostUrl('https://::1:3001'))
})

test('false', t => {
  t.false(isLocalhostUrl('https://example.com'))
  t.false(isLocalhostUrl('http://example.com'))
})
