"use strict";(self.webpackChunkdocs=self.webpackChunkdocs||[]).push([[8190],{3905:function(e,n,t){t.d(n,{Zo:function(){return u},kt:function(){return h}});var o=t(7294);function i(e,n,t){return n in e?Object.defineProperty(e,n,{value:t,enumerable:!0,configurable:!0,writable:!0}):e[n]=t,e}function a(e,n){var t=Object.keys(e);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);n&&(o=o.filter((function(n){return Object.getOwnPropertyDescriptor(e,n).enumerable}))),t.push.apply(t,o)}return t}function r(e){for(var n=1;n<arguments.length;n++){var t=null!=arguments[n]?arguments[n]:{};n%2?a(Object(t),!0).forEach((function(n){i(e,n,t[n])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(t)):a(Object(t)).forEach((function(n){Object.defineProperty(e,n,Object.getOwnPropertyDescriptor(t,n))}))}return e}function l(e,n){if(null==e)return{};var t,o,i=function(e,n){if(null==e)return{};var t,o,i={},a=Object.keys(e);for(o=0;o<a.length;o++)t=a[o],n.indexOf(t)>=0||(i[t]=e[t]);return i}(e,n);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);for(o=0;o<a.length;o++)t=a[o],n.indexOf(t)>=0||Object.prototype.propertyIsEnumerable.call(e,t)&&(i[t]=e[t])}return i}var s=o.createContext({}),c=function(e){var n=o.useContext(s),t=n;return e&&(t="function"==typeof e?e(n):r(r({},n),e)),t},u=function(e){var n=c(e.components);return o.createElement(s.Provider,{value:n},e.children)},p={inlineCode:"code",wrapper:function(e){var n=e.children;return o.createElement(o.Fragment,{},n)}},d=o.forwardRef((function(e,n){var t=e.components,i=e.mdxType,a=e.originalType,s=e.parentName,u=l(e,["components","mdxType","originalType","parentName"]),d=c(t),h=i,f=d["".concat(s,".").concat(h)]||d[h]||p[h]||a;return t?o.createElement(f,r(r({ref:n},u),{},{components:t})):o.createElement(f,r({ref:n},u))}));function h(e,n){var t=arguments,i=n&&n.mdxType;if("string"==typeof e||i){var a=t.length,r=new Array(a);r[0]=d;var l={};for(var s in n)hasOwnProperty.call(n,s)&&(l[s]=n[s]);l.originalType=e,l.mdxType="string"==typeof e?e:i,r[1]=l;for(var c=2;c<a;c++)r[c]=t[c];return o.createElement.apply(null,r)}return o.createElement.apply(null,t)}d.displayName="MDXCreateElement"},6745:function(e,n,t){t.r(n),t.d(n,{frontMatter:function(){return l},contentTitle:function(){return s},metadata:function(){return c},assets:function(){return u},toc:function(){return p},default:function(){return h}});var o=t(7462),i=t(3366),a=(t(7294),t(3905)),r=["components"],l={slug:"inline-functions",title:"Secure against de/recompiling android apps",author:"Daniel Llewellyn",author_url:"https://github.com/dllewellyn",tags:["security","android","safetorun","inline","decompilation","recompilation","kotlin","inline"]},s=void 0,c={permalink:"/safe_to_run/blog/inline-functions",source:"@site/blog/2021-06-24-inline-functions.md",title:"Secure against de/recompiling android apps",description:"Using kotlin Inline functions to help secure against de/recompiling android apps",date:"2021-06-24T00:00:00.000Z",formattedDate:"June 24, 2021",tags:[{label:"security",permalink:"/safe_to_run/blog/tags/security"},{label:"android",permalink:"/safe_to_run/blog/tags/android"},{label:"safetorun",permalink:"/safe_to_run/blog/tags/safetorun"},{label:"inline",permalink:"/safe_to_run/blog/tags/inline"},{label:"decompilation",permalink:"/safe_to_run/blog/tags/decompilation"},{label:"recompilation",permalink:"/safe_to_run/blog/tags/recompilation"},{label:"kotlin",permalink:"/safe_to_run/blog/tags/kotlin"}],readingTime:5.035,truncated:!1,authors:[{name:"Daniel Llewellyn",url:"https://github.com/dllewellyn"}],nextItem:{title:"Emulator detection",permalink:"/safe_to_run/blog/emulator-detection"}},u={authorsImageUrls:[void 0]},p=[{value:"Using kotlin Inline functions to help secure against de/recompiling android apps",id:"using-kotlin-inline-functions-to-help-secure-against-derecompiling-android-apps",children:[],level:3},{value:"The standard approach",id:"the-standard-approach",children:[],level:3},{value:"Inlined function",id:"inlined-function",children:[],level:3},{value:"Conclusions",id:"conclusions",children:[],level:3}],d={toc:p};function h(e){var n=e.components,t=(0,i.Z)(e,r);return(0,a.kt)("wrapper",(0,o.Z)({},d,t,{components:n,mdxType:"MDXLayout"}),(0,a.kt)("h3",{id:"using-kotlin-inline-functions-to-help-secure-against-derecompiling-android-apps"},"Using kotlin Inline functions to help secure against de/recompiling android apps"),(0,a.kt)("p",null,"When trying to attack an android application, attackers often try to circumvent some of the protections you've\nintroduced into your app. For example, you might have a signature check added in order to prevent attackers from adding\nmalware into your app and republishing it:"),(0,a.kt)("p",null,(0,a.kt)("a",{parentName:"p",href:"https://safetorun.github.io/safe_to_run/docs/signature"},"Safe to run - signature verification ")),(0,a.kt)("p",null,"They might also reverse your app to remove any root protection / detection you've added:"),(0,a.kt)("p",null,(0,a.kt)("a",{parentName:"p",href:"https://safetorun.github.io/safe_to_run/docs/rootdetection"},"Safe to run - root detection")),(0,a.kt)("p",null,"Or they might try to remove any other checks you have added, for example, checks to stop it running on an emulator:"),(0,a.kt)("p",null,(0,a.kt)("a",{parentName:"p",href:"https://safetorun.github.io/safe_to_run/docs/emulatorcheck"},"Safe to run - emulator check")),(0,a.kt)("p",null,"In order to make this harder, we can implement these checks using the inline keyword in kotlin. What makes it easy now?\nTo understand how inlining functions can help, we should first look at how easy it is without inlining. If we take an\napplication compiled with the 'Safe to run' reporting checks ",(0,a.kt)("a",{parentName:"p",href:"https://github.com/safetorun/safe_to_run"},"Safe to run")),(0,a.kt)("h3",{id:"the-standard-approach"},"The standard approach"),(0,a.kt)("p",null,"Let's suppose we add this configuration"),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin"},"SafeToRun.init(\n    configure {\n        osDetectionCheck(banAvdEmulator()).error()\n    }\n)\n")),(0,a.kt)("p",null,"To our Application or in MainActivity. Then we run one check at launch (in MainActivity onCreate())"),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin"},'if (SafeToRun.isSafeToRun().anyFailures()) {\n    throw RuntimeException("Abc")\n}\n')),(0,a.kt)("p",null,"And we also add a button to do a check, let's say we have a button like this:"),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-xml"},'<Button android:id="@+id/runSensitiveAction"\n    android:text="Run sensitive action"\n    app:layout_constraintTop_toTopOf="parent"\n    app:layout_constraintStart_toStartOf="parent"\n    app:layout_constraintEnd_toEndOf="parent"\n    android:layout_width="match_parent"\n    android:layout_height="wrap_content"/>\n')),(0,a.kt)("p",null,"And program it up like this:"),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin"},'binding.runSensitiveAction.setOnClickListener {\n    if (SafeToRun.isSafeToRun().anyFailures()) {\n        Toast.makeText(this, "Not safe to run", Toast.LENGTH_LONG).show()\n    } else {\n        Toast.makeText(this, "Performed sensitive action!!", Toast.LENGTH_LONG).show()\n    }\n}\n')),(0,a.kt)("p",null,"When you run the application on an emulator, it will throw an exception. So an attacker might try and take the emulator\ndetection out - let's look at how this application decompiles. We might see something similar to this:"),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-java"},'public final void invoke(SafeToRunConfiguration $this$configure) {\n    Intrinsics.checkNotNullParameter($this$configure,"$receiver");\n    $this$configure.error(OSDetectionCheckKt.osDetectionCheck(this.this$0,C01051.INSTANCE));\n}\n')),(0,a.kt)("p",null,"If we can identify this single line and remove it, then we'll be able to recompile having removed the root detection.\nLet's demonstrate. I'm using apk tool (full instructions on de/recompiling are a bit out of scope for this article but\nI'll add some of the steps for info):"),(0,a.kt)("p",null,(0,a.kt)("inlineCode",{parentName:"p"},"apktool.sh d app-debug.apk")," "),(0,a.kt)("p",null,"This gives me the files as smali. If I look for the line of code doing the config I'll see this:"),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-smali"},"check-cast v1, Lkotlin/jvm/functions/Function1;\n\ninvoke-static {v0, v1}, Lio/github/dllewellyn/safetorun/features/rootdetection/RootDetectionConfigKt;->rootDetection(Landroid/content/Context;Lkotlin/jvm/functions/Function1;)Lio/github/dllewellyn/safetorun/checks/SafeToRunCheck;\n\nmove-result-object v0\n\n.line 94\ninvoke-virtual {p1, v0}, Lio/github/dllewellyn/safetorun/SafeToRunConfiguration;->error(Lio/github/dllewellyn/safetorun/checks/SafeToRunCheck;)V\n")),(0,a.kt)("p",null,"if I remove the final line, it will not run .error() and basically remove the check. So let's do that and recompile:"),(0,a.kt)("p",null,(0,a.kt)("inlineCode",{parentName:"p"},"apktool.sh b -f -d app-debug")),(0,a.kt)("p",null,"Zipalign the result "),(0,a.kt)("p",null,(0,a.kt)("inlineCode",{parentName:"p"},"./zipalign -v 4 app-debug.apk app-debug-aligned.apk")," "),(0,a.kt)("p",null,"Then sign (password is android)"),(0,a.kt)("p",null,(0,a.kt)("inlineCode",{parentName:"p"},"apksigner sign --ks ~/.android/debug.keystore output.apk")," "),(0,a.kt)("p",null,"Then install ",(0,a.kt)("inlineCode",{parentName:"p"},"adb install app-debug-aligned.apk")," and you'll see it runs. "),(0,a.kt)("p",null,"Click the 'sensitive button' and the action is performed. "),(0,a.kt)("p",null,"The issue This was a fairly straight forward set of steps to remove our check, because you've removed the\nconfiguration in a single place, every place that safe to run is called has now been removed. In order to make all of this harder, we'd ideally want to make it so that if we add two\nchecks, it takes double the effort to remove them (and three checks triple.. etc etc). "),(0,a.kt)("p",null,"Inlining In SafeToRun 1.0.3 we have introduced a new function which uses inline functions to make de/recompilation harder.\nAs a reminder, in usual compilation when you call a function, the compiled code looks similar to your un-compiled code -\nin the sense that it has a reference to that function, and jumps to where that function is in the binary. When we Inline a\nfunction, the whole function you're calling is copied inside the calling function at compile time. "),(0,a.kt)("h3",{id:"inlined-function"},"Inlined function"),(0,a.kt)("p",null,"Let's add this function to MainActivity:"),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin"},"private inline fun canIRun(actionOnFailure: () -> Unit) {\n    if (safeToRun(buildSafeToRunCheckList {\n            add {\n                banAvdEmulatorCheck()\n            }\n        })()) {\n        actionOnFailure()\n    }\n}\n")),(0,a.kt)("p",null,"One thing to note is that the syntax for the inlined versioning of Safe to Run is still in active development at the\ntime of writing, be sure to check the documentation for the most up-to-date syntax If also add this block of code into\nthe button click, and also into onCreate for MainActivity:"),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin"},'canIRun { throw RuntimeException("Error with safe to run") }\n')),(0,a.kt)("p",null,"We'll repeat the compilation and recompilation stage. After a bit of digging, I found something that looks like this:"),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-smali"},".method public bridge synthetic invoke()Ljava/lang/Object;\n.locals 1\n\ninvoke-virtual {p0}, Lcom/andro/secure/MainActivity$canIRun$$inlined$safeToRun$2;->invoke()Z\n\nmove-result v0\n\ninvoke-static {v0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;\n\nmove-result-object v0\n\nreturn-object v0\n\n.end method\n")),(0,a.kt)("p",null,"and I replaced it with this code:"),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-smali"},".method public bridge synthetic invoke()Ljava/lang/Object;\n.locals 1\n\ninvoke-virtual {p0}, Lcom/andro/secure/MainActivity$canIRun$$inlined$safeToRun$2;->invoke()Z\n\nmove-result v0\n\ninvoke-static {v0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;\n\nmove-result-object v0\n\nconst v0, 0\n\ninvoke-static {v0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;\n\nmove-result-object v0\n\nreturn-object v0\n.end method\n\n")),(0,a.kt)("p",null,"All this does is returns 'false' (i.e. the check returns false in the inlined version we return a boolean indicating\ntrue if a check failed rather than a SafeToRunReport)"),(0,a.kt)("p",null,"If we recompile using the same steps as before, you'll find that the app runs. However, clicking the button will still\ncause a RuntimeException. The reason is that the entire functions call chain is duplicated inside the button click. This\nadds some size to the binary but now an attacker would need to find the function in every place where you have called '\ncanIRun' making the job much harder. "),(0,a.kt)("h3",{id:"conclusions"},"Conclusions"),(0,a.kt)("p",null,"In this article we've demonstrated that by using inline functions (for\nthe entire chain) and no classes etc, it is exponentially more difficult to remove device safety checks the more checks\nyou add. "),(0,a.kt)("p",null,"If you were to add a single check at runtime, it would not be any more difficult - however if you litter your\ncode with Safe to run checks, you will find it harder to decompile and remove those checks. As ever, it's\nimpossible to have a foolproof way of preventing re/decompiling due to the nature of the problem - but inlining functions\nin this way can help make it harder for an attacker"))}h.isMDXComponent=!0}}]);