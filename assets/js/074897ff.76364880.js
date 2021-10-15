(window.webpackJsonp=window.webpackJsonp||[]).push([[5],{125:function(e,t,n){"use strict";n.d(t,"a",(function(){return p})),n.d(t,"b",(function(){return g}));var r=n(0),a=n.n(r);function i(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function o(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function l(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?o(Object(n),!0).forEach((function(t){i(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):o(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function c(e,t){if(null==e)return{};var n,r,a=function(e,t){if(null==e)return{};var n,r,a={},i=Object.keys(e);for(r=0;r<i.length;r++)n=i[r],t.indexOf(n)>=0||(a[n]=e[n]);return a}(e,t);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(e);for(r=0;r<i.length;r++)n=i[r],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(a[n]=e[n])}return a}var u=a.a.createContext({}),s=function(e){var t=a.a.useContext(u),n=t;return e&&(n="function"==typeof e?e(t):l(l({},t),e)),n},p=function(e){var t=s(e.components);return a.a.createElement(u.Provider,{value:t},e.children)},f={inlineCode:"code",wrapper:function(e){var t=e.children;return a.a.createElement(a.a.Fragment,{},t)}},d=a.a.forwardRef((function(e,t){var n=e.components,r=e.mdxType,i=e.originalType,o=e.parentName,u=c(e,["components","mdxType","originalType","parentName"]),p=s(n),d=r,g=p["".concat(o,".").concat(d)]||p[d]||f[d]||i;return n?a.a.createElement(g,l(l({ref:t},u),{},{components:n})):a.a.createElement(g,l({ref:t},u))}));function g(e,t){var n=arguments,r=t&&t.mdxType;if("string"==typeof e||r){var i=n.length,o=new Array(i);o[0]=d;var l={};for(var c in t)hasOwnProperty.call(t,c)&&(l[c]=t[c]);l.originalType=e,l.mdxType="string"==typeof e?e:r,o[1]=l;for(var u=2;u<i;u++)o[u]=n[u];return a.a.createElement.apply(null,o)}return a.a.createElement.apply(null,n)}d.displayName="MDXCreateElement"},72:function(e,t,n){"use strict";n.r(t),n.d(t,"frontMatter",(function(){return l})),n.d(t,"metadata",(function(){return c})),n.d(t,"toc",(function(){return u})),n.d(t,"default",(function(){return p}));var r=n(3),a=n(8),i=(n(0),n(125)),o=["components"],l={id:"gettingstarted",title:"Getting started",slug:"/gettingstarted"},c={unversionedId:"input/gettingstarted",id:"input/gettingstarted",isDocsHomePage:!1,title:"Getting started",description:"Gradle:",source:"@site/docs/input/gettingstarted.md",sourceDirName:"input",slug:"/gettingstarted",permalink:"/safe_to_run/docs/gettingstarted",editUrl:"https://github.com/safetorun/safe_to_run/edit/master/docs/docs/input/gettingstarted.md",version:"current",frontMatter:{id:"gettingstarted",title:"Getting started",slug:"/gettingstarted"},sidebar:"someSidebar",previous:{title:"Install origin",permalink:"/safe_to_run/docs/installorigin"},next:{title:"URL verification",permalink:"/safe_to_run/docs/verifyurls"}},u=[],s={toc:u};function p(e){var t=e.components,n=Object(a.a)(e,o);return Object(i.b)("wrapper",Object(r.a)({},s,n,{components:t,mdxType:"MDXLayout"}),Object(i.b)("p",null,"Gradle:"),Object(i.b)("pre",null,Object(i.b)("code",{parentName:"pre",className:"language-groovy"},'implementation "io.github.dllewellyn.safetorun:inputverification:$latest_version"\n')),Object(i.b)("p",null,"Sample for URL verification"),Object(i.b)("pre",null,Object(i.b)("code",{parentName:"pre",className:"language-kotlin"},'val url = intent?.extras?.getString("url")\nval isUrlOk = url?.urlVerification {\n    "safetorun.com".allowHost()\n    allowParameter {\n        allowedType = AllowedType.String\n        parameterName = "a_token"\n    }\n}\n\nif (isUrlOk != true) {\n    throw RuntimeException("Failed validation!!")\n}\n')),Object(i.b)("p",null,"And a sample for intent verification"),Object(i.b)("pre",null,Object(i.b)("code",{parentName:"pre",className:"language-kotlin"},'\nintent.verify {\n    urlConfiguration { "safetorun.com".allowHost() }\n\n    actionOnSuccess = {\n        intent?.extras?.getString("url")\n            ?.let { "${it}?sensitive_token=pleasekeepmesecret" }\n            ?.let(webView::loadUrl)\n    }\n}\n')))}p.isMDXComponent=!0}}]);