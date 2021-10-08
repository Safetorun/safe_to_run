(window.webpackJsonp=window.webpackJsonp||[]).push([[35],{106:function(e,t,n){"use strict";n.r(t),n.d(t,"frontMatter",(function(){return o})),n.d(t,"metadata",(function(){return c})),n.d(t,"toc",(function(){return s})),n.d(t,"default",(function(){return p}));var i=n(3),r=n(8),a=(n(0),n(125)),l=["components"],o={id:"files",title:"File verification",slug:"/filesverification"},c={unversionedId:"input/files",id:"input/files",isDocsHomePage:!1,title:"File verification",description:"Verify files",source:"@site/docs/input/files.md",sourceDirName:"input",slug:"/filesverification",permalink:"/safe_to_run/docs/filesverification",editUrl:"https://github.com/safetorun/safe_to_run/edit/master/docs/docs/input/files.md",version:"current",frontMatter:{id:"files",title:"File verification",slug:"/filesverification"},sidebar:"someSidebar",previous:{title:"URL verification",permalink:"/safe_to_run/docs/verifyurls"},next:{title:"Intent verification",permalink:"/safe_to_run/docs/intentverification"}},s=[{value:"Verify files",id:"verify-files",children:[]},{value:"Allow no private apps",id:"allow-no-private-apps",children:[]},{value:"Allow specific private file",id:"allow-specific-private-file",children:[]},{value:"Allow all files in a directory",id:"allow-all-files-in-a-directory",children:[]},{value:"Allow all files and subdirectories",id:"allow-all-files-and-subdirectories",children:[]},{value:"Allow any file",id:"allow-any-file",children:[]}],f={toc:s};function p(e){var t=e.components,n=Object(r.a)(e,l);return Object(a.b)("wrapper",Object(i.a)({},f,n,{components:t,mdxType:"MDXLayout"}),Object(a.b)("h3",{id:"verify-files"},"Verify files"),Object(a.b)("p",null,"Imagine you have written code that looks like this:"),Object(a.b)("pre",null,Object(a.b)("code",{parentName:"pre",className:"language-kotlin"},"if (intent.action == Intent.ACTION_SEND) {\n    val uri = intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as Uri\n    sendData(contentResolver.openInputStream(uri))\n}\n")),Object(a.b)("p",null,"Your application in this case might be at risk of an attack that looks like this:"),Object(a.b)("pre",null,Object(a.b)("code",{parentName:"pre",className:"language-kotlin"},'Intent(Intent.ACTION_SEND).apply {\n    putExtra(\n        Intent.EXTRA_STREAM,\n        Uri.parse("file:///data/data/${it.packageName}/db/sensitive.db")\n    )\n}\n')),Object(a.b)("p",null,"In this example, we might allow an attacker to send sensitive keys, databases (etc)."),Object(a.b)("h3",{id:"allow-no-private-apps"},"Allow no private apps"),Object(a.b)("p",null,"To prevent this type of attack, we can use the verify command in order to check a file\n(or a URI) before opening it."),Object(a.b)("pre",null,Object(a.b)("code",{parentName:"pre",className:"language-kotlin"},"val isFileSafeToOpen = uri.verifyFile(this) {}\n")),Object(a.b)("p",null,"By default, no files from your private directory is allowed - which is what you want in most cases."),Object(a.b)("h3",{id:"allow-specific-private-file"},"Allow specific private file"),Object(a.b)("p",null,"We can allow a specific file"),Object(a.b)("pre",null,Object(a.b)("code",{parentName:"pre",className:"language-kotlin"},'val isFileSafeToOpen = uri.verifyFile(this) {\n    // This\n    File(context.filesDir + "files/", "safe_to_read.txt").allowExactFile()\n\n    // Is the same as this:\n    addAllowedExactFile(File(context.filesDir + "files/", "safe_to_read.txt"))\n}\n')),Object(a.b)("h3",{id:"allow-all-files-in-a-directory"},"Allow all files in a directory"),Object(a.b)("p",null,"Instead of this, we can add a directory and allow all files in that directory"),Object(a.b)("pre",null,Object(a.b)("code",{parentName:"pre",className:"language-kotlin"},"val isFileSafeToOpen = uri.verifyFile(this) {\n    // This\n    addAllowedParentDirectory(context.filesDir.allowDirectory())\n\n    // Is the same as this:\n    FileUriMatcherBuilder.FileUriMatcherCheck(\n        context.filesDir,\n        false\n    )\n}\n")),Object(a.b)("h3",{id:"allow-all-files-and-subdirectories"},"Allow all files and subdirectories"),Object(a.b)("p",null,"At the moment ",Object(a.b)("inlineCode",{parentName:"p"},"/data/data/com.safe.to.run/files/abc.txt")," would be allowed,\nbut ",Object(a.b)("inlineCode",{parentName:"p"},"/data/data/com.safe.to.run/files/subdir/abc.txt"),"\nwould not. To allow subdirectories:"),Object(a.b)("pre",null,Object(a.b)("code",{parentName:"pre",className:"language-kotlin"},"val isFileSafeToOpen = uri.verifyFile(this) {\n    // This\n    addAllowedParentDirectory(context.filesDir.allowDirectoryAndSubdirectories())\n\n    // Is the same as this:\n    FileUriMatcherBuilder.FileUriMatcherCheck(\n        context.filesDir,\n        true\n    )\n}\n")),Object(a.b)("h3",{id:"allow-any-file"},"Allow any file"),Object(a.b)("p",null,"We would not recommend doing this:"),Object(a.b)("pre",null,Object(a.b)("code",{parentName:"pre",className:"language-kotlin"},"val isFileSafeToOpen = uri.verifyFile(this) {\n    allowAnyFile = true\n}\n")))}p.isMDXComponent=!0},125:function(e,t,n){"use strict";n.d(t,"a",(function(){return p})),n.d(t,"b",(function(){return b}));var i=n(0),r=n.n(i);function a(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function l(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(e);t&&(i=i.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,i)}return n}function o(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?l(Object(n),!0).forEach((function(t){a(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):l(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function c(e,t){if(null==e)return{};var n,i,r=function(e,t){if(null==e)return{};var n,i,r={},a=Object.keys(e);for(i=0;i<a.length;i++)n=a[i],t.indexOf(n)>=0||(r[n]=e[n]);return r}(e,t);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);for(i=0;i<a.length;i++)n=a[i],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(r[n]=e[n])}return r}var s=r.a.createContext({}),f=function(e){var t=r.a.useContext(s),n=t;return e&&(n="function"==typeof e?e(t):o(o({},t),e)),n},p=function(e){var t=f(e.components);return r.a.createElement(s.Provider,{value:t},e.children)},u={inlineCode:"code",wrapper:function(e){var t=e.children;return r.a.createElement(r.a.Fragment,{},t)}},d=r.a.forwardRef((function(e,t){var n=e.components,i=e.mdxType,a=e.originalType,l=e.parentName,s=c(e,["components","mdxType","originalType","parentName"]),p=f(n),d=i,b=p["".concat(l,".").concat(d)]||p[d]||u[d]||a;return n?r.a.createElement(b,o(o({ref:t},s),{},{components:n})):r.a.createElement(b,o({ref:t},s))}));function b(e,t){var n=arguments,i=t&&t.mdxType;if("string"==typeof e||i){var a=n.length,l=new Array(a);l[0]=d;var o={};for(var c in t)hasOwnProperty.call(t,c)&&(o[c]=t[c]);o.originalType=e,o.mdxType="string"==typeof e?e:i,l[1]=o;for(var s=2;s<a;s++)l[s]=n[s];return r.a.createElement.apply(null,l)}return r.a.createElement.apply(null,n)}d.displayName="MDXCreateElement"}}]);