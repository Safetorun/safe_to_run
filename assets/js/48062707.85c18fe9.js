(window.webpackJsonp=window.webpackJsonp||[]).push([[16],{119:function(e,t,n){"use strict";n.d(t,"a",(function(){return p})),n.d(t,"b",(function(){return m}));var a=n(0),r=n.n(a);function o(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function i(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);t&&(a=a.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,a)}return n}function c(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?i(Object(n),!0).forEach((function(t){o(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):i(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function l(e,t){if(null==e)return{};var n,a,r=function(e,t){if(null==e)return{};var n,a,r={},o=Object.keys(e);for(a=0;a<o.length;a++)n=o[a],t.indexOf(n)>=0||(r[n]=e[n]);return r}(e,t);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);for(a=0;a<o.length;a++)n=o[a],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(r[n]=e[n])}return r}var u=r.a.createContext({}),s=function(e){var t=r.a.useContext(u),n=t;return e&&(n="function"==typeof e?e(t):c(c({},t),e)),n},p=function(e){var t=s(e.components);return r.a.createElement(u.Provider,{value:t},e.children)},d={inlineCode:"code",wrapper:function(e){var t=e.children;return r.a.createElement(r.a.Fragment,{},t)}},b=r.a.forwardRef((function(e,t){var n=e.components,a=e.mdxType,o=e.originalType,i=e.parentName,u=l(e,["components","mdxType","originalType","parentName"]),p=s(n),b=a,m=p["".concat(i,".").concat(b)]||p[b]||d[b]||o;return n?r.a.createElement(m,c(c({ref:t},u),{},{components:n})):r.a.createElement(m,c({ref:t},u))}));function m(e,t){var n=arguments,a=t&&t.mdxType;if("string"==typeof e||a){var o=n.length,i=new Array(o);i[0]=b;var c={};for(var l in t)hasOwnProperty.call(t,l)&&(c[l]=t[l]);c.originalType=e,c.mdxType="string"==typeof e?e:a,i[1]=c;for(var u=2;u<o;u++)i[u]=n[u];return r.a.createElement.apply(null,i)}return r.a.createElement.apply(null,n)}b.displayName="MDXCreateElement"},87:function(e,t,n){"use strict";n.r(t),n.d(t,"frontMatter",(function(){return c})),n.d(t,"metadata",(function(){return l})),n.d(t,"toc",(function(){return u})),n.d(t,"default",(function(){return p}));var a=n(3),r=n(8),o=(n(0),n(119)),i=["components"],c={slug:"emulator-detection",title:"Secure android apps with Safe to run",author:"Daniel Llewellyn",author_url:"https://github.com/dllewellyn",tags:["security","android","safetorun","kotlin"]},l={permalink:"/safe_to_run/blog/emulator-detection",source:"@site/blog/2021-06-22-safe-to-run.md",title:"Secure android apps with Safe to run",description:"No library or app can guarantee not running on a rooted phone because of the nature of rooted phones, and any tamper detection could be removed or changed in reality \u2014 this app should work with most attackers, and make it hard enough to make it not worth it for many others.",date:"2021-06-22T00:00:00.000Z",formattedDate:"June 22, 2021",tags:[{label:"security",permalink:"/safe_to_run/blog/tags/security"},{label:"android",permalink:"/safe_to_run/blog/tags/android"},{label:"safetorun",permalink:"/safe_to_run/blog/tags/safetorun"},{label:"kotlin",permalink:"/safe_to_run/blog/tags/kotlin"}],readingTime:1.54,truncated:!1,prevItem:{title:"Emulator detection",permalink:"/safe_to_run/blog/emulator-detection"}},u=[{value:"Checks",id:"checks",children:[]},{value:"Sample usage",id:"sample-usage",children:[]}],s={toc:u};function p(e){var t=e.components,n=Object(r.a)(e,i);return Object(o.b)("wrapper",Object(a.a)({},s,n,{components:t,mdxType:"MDXLayout"}),Object(o.b)("div",{className:"admonition admonition-danger alert alert--danger"},Object(o.b)("div",{parentName:"div",className:"admonition-heading"},Object(o.b)("h5",{parentName:"div"},Object(o.b)("span",{parentName:"h5",className:"admonition-icon"},Object(o.b)("svg",{parentName:"span",xmlns:"http://www.w3.org/2000/svg",width:"12",height:"16",viewBox:"0 0 12 16"},Object(o.b)("path",{parentName:"svg",fillRule:"evenodd",d:"M5.05.31c.81 2.17.41 3.38-.52 4.31C3.55 5.67 1.98 6.45.9 7.98c-1.45 2.05-1.7 6.53 3.53 7.7-2.2-1.16-2.67-4.52-.3-6.61-.61 2.03.53 3.33 1.94 2.86 1.39-.47 2.3.53 2.27 1.67-.02.78-.31 1.44-1.13 1.81 3.42-.59 4.78-3.42 4.78-5.56 0-2.84-2.53-3.22-1.25-5.61-1.52.13-2.03 1.13-1.89 2.75.09 1.08-1.02 1.8-1.86 1.33-.67-.41-.66-1.19-.06-1.78C8.18 5.31 8.68 2.45 5.05.32L5.03.3l.02.01z"}))),"danger")),Object(o.b)("div",{parentName:"div",className:"admonition-content"},Object(o.b)("p",{parentName:"div"},"No library or app can guarantee not running on a rooted phone because of the nature of rooted phones, and any tamper detection could be removed or changed in reality \u2014 this app should work with most attackers, and make it hard enough to make it not worth it for many others.\nBackground"))),Object(o.b)("p",null,"Safe to run is intended to provide a layer of security for Android applications from rooted phones, reverse engineering, binary modification, malicious apps and some security vulnerabilities."),Object(o.b)("p",null,"In principle, you set the parameters for a safe device, (one where the debugger is not attached, one with a minimum OS version, one not rooted etc) and ask \u2018is it safe to run\u2019."),Object(o.b)("p",null,"You know best the time and place to ask the question-maybe you do it on app launch and throw an exception, maybe you ask when some tries to make a payment to someone else and reject the payment or maybe you do it before retrieving some data from the backend."),Object(o.b)("h3",{id:"checks"},"Checks"),Object(o.b)("p",null,"We have the following checks that are configurable in safe to run"),Object(o.b)("ul",null,Object(o.b)("li",{parentName:"ul"},"Signature checks \u2014 check the signature of the binary (set multiple for multiple certs)"),Object(o.b)("li",{parentName:"ul"},"Debug check \u2014 check if the debugger is attached or if the app is debuggable"),Object(o.b)("li",{parentName:"ul"},"Device check \u2014 blacklist a combination of OS version and device manufacturer"),Object(o.b)("li",{parentName:"ul"},"Root check \u2014 check for signs which point to the device being rooted"),Object(o.b)("li",{parentName:"ul"},"Other packages check \u2014 check for the presence of other packages, e.g. You might not want to run if you know a specific app is running because it\u2019s known malware that might attack your app"),Object(o.b)("li",{parentName:"ul"},"Install origin check \u2014 check for the installing package of your app, this can help to make reverse engineering harder")),Object(o.b)("h3",{id:"sample-usage"},"Sample usage"),Object(o.b)("p",null,"Every time you want to run a check execute:"),Object(o.b)("p",null,Object(o.b)("inlineCode",{parentName:"p"},"SafeToRun.safeToRun()")))}p.isMDXComponent=!0}}]);