/*! For license information please see c4f5d8e4.249fa668.js.LICENSE.txt */
(window.webpackJsonp=window.webpackJsonp||[]).push([[25],{158:function(e,t,a){var n;!function(){"use strict";var a={}.hasOwnProperty;function r(){for(var e=[],t=0;t<arguments.length;t++){var n=arguments[t];if(n){var l=typeof n;if("string"===l||"number"===l)e.push(n);else if(Array.isArray(n)){if(n.length){var o=r.apply(null,n);o&&e.push(o)}}else if("object"===l)if(n.toString===Object.prototype.toString)for(var s in n)a.call(n,s)&&n[s]&&e.push(s);else e.push(n.toString())}}return e.join(" ")}e.exports?(r.default=r,e.exports=r):void 0===(n=function(){return r}.apply(t,[]))||(e.exports=n)}()},94:function(e,t,a){"use strict";a.r(t),a.d(t,"default",(function(){return h}));var n=a(3),r=a(0),l=a.n(r),o=a(158),s=a.n(o),c=a(114),i=a(113),u=a(16),d=a(116),m=a(95),g=a.n(m);const p=[{title:"Easy to Use",imageUrl:"img/undraw_docusaurus_mountain.svg",description:l.a.createElement(l.a.Fragment,null,"Docusaurus was designed from the ground up to be easily installed and used to get your website up and running quickly.")},{title:"Focus on What Matters",imageUrl:"img/undraw_docusaurus_tree.svg",description:l.a.createElement(l.a.Fragment,null,"Docusaurus lets you focus on your docs, and we'll do the chores. Go ahead and move your docs into the ",l.a.createElement("code",null,"docs")," directory.")},{title:"Powered by React",imageUrl:"img/undraw_docusaurus_react.svg",description:l.a.createElement(l.a.Fragment,null,"Extend or customize your website layout by reusing React. Docusaurus can be extended while reusing the same header and footer.")}];function f({imageUrl:e,title:t,description:a}){const n=Object(d.a)(e);return l.a.createElement("div",{className:s()("col col--4",g.a.feature)},n&&l.a.createElement("div",{className:"text--center"},l.a.createElement("img",{className:g.a.featureImage,src:n,alt:t})),l.a.createElement("h3",null,t),l.a.createElement("p",null,a))}function h(){const e=Object(u.default)(),{siteConfig:t={}}=e;return l.a.createElement(c.a,{title:`Hello from ${t.title}`,description:"Description will go into a meta tag in <head />"},l.a.createElement("div",{className:g.a.hero},l.a.createElement("header",null,l.a.createElement("h1",null,t.title),l.a.createElement("p",null,t.tagline),l.a.createElement("div",{className:g.a.buttons},l.a.createElement(i.a,{to:Object(d.a)("docs/")},"Get Started"))),l.a.createElement("main",null,p&&p.length>0&&l.a.createElement("section",{className:g.a.section},l.a.createElement("div",{className:g.a.features},p.map(((e,t)=>l.a.createElement(f,Object(n.a)({key:t},e)))))))))}}}]);