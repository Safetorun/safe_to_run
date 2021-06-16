(window.webpackJsonp=window.webpackJsonp||[]).push([[35],{103:function(e,t,n){"use strict";n.r(t),n.d(t,"frontMatter",(function(){return i})),n.d(t,"metadata",(function(){return s})),n.d(t,"toc",(function(){return l})),n.d(t,"default",(function(){return p}));var r=n(3),o=n(7),c=(n(0),n(119)),a=["components"],i={id:"combinechecks",title:"Combine checks",slug:"/combinechecks"},s={unversionedId:"combinechecks",id:"combinechecks",isDocsHomePage:!1,title:"Combine checks",description:"Safe to run (Default) provides the ability to combine multiple checks together, and also",source:"@site/docs/combine_checks.md",sourceDirName:".",slug:"/combinechecks",permalink:"/safe_to_run/docs/combinechecks",editUrl:"https://github.com/facebook/docusaurus/edit/master/website/docs/combine_checks.md",version:"current",frontMatter:{id:"combinechecks",title:"Combine checks",slug:"/combinechecks"},sidebar:"someSidebar",previous:{title:"Getting started",permalink:"/safe_to_run/docs/gettingstarted"},next:{title:"Root detection",permalink:"/safe_to_run/docs/rootdetection"}},l=[{value:"Unless",id:"unless",children:[]}],u={toc:l};function p(e){var t=e.components,n=Object(o.a)(e,a);return Object(c.b)("wrapper",Object(r.a)({},u,n,{components:t,mdxType:"MDXLayout"}),Object(c.b)("p",null,"Safe to run (Default) provides the ability to combine multiple checks together, and also\nhave one check by passes to the check. For example, this rule:"),Object(c.b)("pre",null,Object(c.b)("code",{parentName:"pre",className:"language-kotlin"},'safeToRunCombinedCheck(\n    listOf(\n        { bannedHardwareCheck("hardware") },\n        { bannedBoardCheck("board") }\n    )\n)\n')),Object(c.b)("p",null,"Produces a check that will fail if either the hardware is 'hardware'\nor the board is 'board'"),Object(c.b)("h3",{id:"unless"},"Unless"),Object(c.b)("p",null,"An unless check is a second list of parameters, which if this doesn't fail (e.g. it returns\nfalse) will negate the failure in the first list. For example"),Object(c.b)("pre",null,Object(c.b)("code",{parentName:"pre",className:"language-kotlin"},"safeToRunCombinedCheck(\n    listOf { installOriginCheckWithDefaultsCheck() },\n    listOf { !BuildConfig.DEBUG }\n)\n")),Object(c.b)("p",null,"If the install origins aren't the defaults (e.g. amazon or google play store) then the\ncheck will fail UNLESS we're running a debug build - in which case the check will pass"))}p.isMDXComponent=!0},119:function(e,t,n){"use strict";n.d(t,"a",(function(){return p})),n.d(t,"b",(function(){return d}));var r=n(0),o=n.n(r);function c(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function a(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function i(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?a(Object(n),!0).forEach((function(t){c(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):a(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function s(e,t){if(null==e)return{};var n,r,o=function(e,t){if(null==e)return{};var n,r,o={},c=Object.keys(e);for(r=0;r<c.length;r++)n=c[r],t.indexOf(n)>=0||(o[n]=e[n]);return o}(e,t);if(Object.getOwnPropertySymbols){var c=Object.getOwnPropertySymbols(e);for(r=0;r<c.length;r++)n=c[r],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(o[n]=e[n])}return o}var l=o.a.createContext({}),u=function(e){var t=o.a.useContext(l),n=t;return e&&(n="function"==typeof e?e(t):i(i({},t),e)),n},p=function(e){var t=u(e.components);return o.a.createElement(l.Provider,{value:t},e.children)},b={inlineCode:"code",wrapper:function(e){var t=e.children;return o.a.createElement(o.a.Fragment,{},t)}},f=o.a.forwardRef((function(e,t){var n=e.components,r=e.mdxType,c=e.originalType,a=e.parentName,l=s(e,["components","mdxType","originalType","parentName"]),p=u(n),f=r,d=p["".concat(a,".").concat(f)]||p[f]||b[f]||c;return n?o.a.createElement(d,i(i({ref:t},l),{},{components:n})):o.a.createElement(d,i({ref:t},l))}));function d(e,t){var n=arguments,r=t&&t.mdxType;if("string"==typeof e||r){var c=n.length,a=new Array(c);a[0]=f;var i={};for(var s in t)hasOwnProperty.call(t,s)&&(i[s]=t[s]);i.originalType=e,i.mdxType="string"==typeof e?e:r,a[1]=i;for(var l=2;l<c;l++)a[l]=n[l];return o.a.createElement.apply(null,a)}return o.a.createElement.apply(null,n)}f.displayName="MDXCreateElement"}}]);