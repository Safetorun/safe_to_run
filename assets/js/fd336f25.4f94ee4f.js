(window.webpackJsonp=window.webpackJsonp||[]).push([[46],{116:function(e,t,r){"use strict";r.r(t),r.d(t,"frontMatter",(function(){return i})),r.d(t,"metadata",(function(){return c})),r.d(t,"toc",(function(){return l})),r.d(t,"default",(function(){return s}));var n=r(3),o=r(8),a=(r(0),r(121)),u=["components"],i={title:"Blog Plugin",author:"Fanny Vieira",authorTitle:"Maintainer of Docusaurus",authorURL:"https://github.com/fanny",authorImageURL:"https://github.com/fanny.png",authorTwitter:"fannyvieiira",tags:["blog","docusaurus"]},c={permalink:"/safe_to_run/blog/2020/04/14/blog-plugin",source:"@site/blog/2020-04-14-blog-plugin.md",title:"Blog Plugin",description:"You can use our blog plugin to do your posts",date:"2020-04-14T00:00:00.000Z",formattedDate:"April 14, 2020",tags:[{label:"blog",permalink:"/safe_to_run/blog/tags/blog"},{label:"docusaurus",permalink:"/safe_to_run/blog/tags/docusaurus"}],readingTime:.145,truncated:!0,prevItem:{title:"A large blog post",permalink:"/safe_to_run/blog/2020/04/14/large-blog-post"},nextItem:{title:"Welcome",permalink:"/safe_to_run/blog/welcome"}},l=[],p={toc:l};function s(e){var t=e.components,r=Object(o.a)(e,u);return Object(a.b)("wrapper",Object(n.a)({},p,r,{components:t,mdxType:"MDXLayout"}),Object(a.b)("p",null,"You can use our blog plugin to do your posts"))}s.isMDXComponent=!0},121:function(e,t,r){"use strict";r.d(t,"a",(function(){return s})),r.d(t,"b",(function(){return g}));var n=r(0),o=r.n(n);function a(e,t,r){return t in e?Object.defineProperty(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}):e[t]=r,e}function u(e,t){var r=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),r.push.apply(r,n)}return r}function i(e){for(var t=1;t<arguments.length;t++){var r=null!=arguments[t]?arguments[t]:{};t%2?u(Object(r),!0).forEach((function(t){a(e,t,r[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(r)):u(Object(r)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(r,t))}))}return e}function c(e,t){if(null==e)return{};var r,n,o=function(e,t){if(null==e)return{};var r,n,o={},a=Object.keys(e);for(n=0;n<a.length;n++)r=a[n],t.indexOf(r)>=0||(o[r]=e[r]);return o}(e,t);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);for(n=0;n<a.length;n++)r=a[n],t.indexOf(r)>=0||Object.prototype.propertyIsEnumerable.call(e,r)&&(o[r]=e[r])}return o}var l=o.a.createContext({}),p=function(e){var t=o.a.useContext(l),r=t;return e&&(r="function"==typeof e?e(t):i(i({},t),e)),r},s=function(e){var t=p(e.components);return o.a.createElement(l.Provider,{value:t},e.children)},f={inlineCode:"code",wrapper:function(e){var t=e.children;return o.a.createElement(o.a.Fragment,{},t)}},b=o.a.forwardRef((function(e,t){var r=e.components,n=e.mdxType,a=e.originalType,u=e.parentName,l=c(e,["components","mdxType","originalType","parentName"]),s=p(r),b=n,g=s["".concat(u,".").concat(b)]||s[b]||f[b]||a;return r?o.a.createElement(g,i(i({ref:t},l),{},{components:r})):o.a.createElement(g,i({ref:t},l))}));function g(e,t){var r=arguments,n=t&&t.mdxType;if("string"==typeof e||n){var a=r.length,u=new Array(a);u[0]=b;var i={};for(var c in t)hasOwnProperty.call(t,c)&&(i[c]=t[c]);i.originalType=e,i.mdxType="string"==typeof e?e:n,u[1]=i;for(var l=2;l<a;l++)u[l]=r[l];return o.a.createElement.apply(null,u)}return o.a.createElement.apply(null,r)}b.displayName="MDXCreateElement"}}]);