# multi_module
multi_module for weather project

<b>프로젝트 구조</b>
```
.root
│  └─modules
│      ├─module-api
│      ├─module-common
│      └─module-web
├─gradle
│  └─wrapper
├─module-api
│  ├─build
│  │  ├─classes
│  │  │  └─java
│  │  │      └─main
│  │  │          └─org
│  │  │              └─example
│  │  ├─generated
│  │  │  └─sources
│  │  │      ├─annotationProcessor
│  │  │      │  └─java
│  │  │      │      └─main
│  │  │      └─headers
│  │  │          └─java
│  │  │              └─main
│  │  └─tmp
│  │      └─compileJava
│  │          └─compileTransaction
│  │              ├─backup-dir
│  │              └─stash-dir
│  ├─out
│  │  └─production
│  │      └─classes
│  └─src
│      ├─main
│      │  ├─generated
│      │  ├─java
│      │  │  └─org
│      │  │      └─example
│      │  └─resources
│      └─test
│          ├─java
│          └─resources
├─module-common
│  ├─build
│  │  ├─classes
│  │  │  └─java
│  │  │      └─main
│  │  │          └─org
│  │  │              └─example
│  │  ├─generated
│  │  │  └─sources
│  │  │      ├─annotationProcessor
│  │  │      │  └─java
│  │  │      │      └─main
│  │  │      └─headers
│  │  │          └─java
│  │  │              └─main
│  │  ├─libs
│  │  ├─resources
│  │  │  └─main
│  │  └─tmp
│  │      ├─compileJava
│  │      │  └─compileTransaction
│  │      │      ├─backup-dir
│  │      │      └─stash-dir
│  │      └─jar
│  ├─out
│  │  └─production
│  │      ├─classes
│  │      │  └─org
│  │      │      └─example
│  │      └─resources
│  └─src
│      ├─main
│      │  ├─generated
│      │  ├─java
│      │  │  └─org
│      │  │      └─example
│      │  └─resources
│      └─test
│          ├─java
│          └─resources
└─module-web
    ├─build
    │  ├─classes
    │  │  └─java
    │  │      └─main
    │  │          └─org
    │  │              └─example
    │  ├─generated
    │  │  └─sources
    │  │      ├─annotationProcessor
    │  │      │  └─java
    │  │      │      └─main
    │  │      └─headers
    │  │          └─java
    │  │              └─main
    │  ├─libs
    │  └─tmp
    │      ├─compileJava
    │      │  └─compileTransaction
    │      │      ├─backup-dir
    │      │      └─stash-dir
    │      └─jar
    ├─out
    │  └─production
    │      └─classes
    │          └─org
    │              └─example
    └─src
        ├─main
        │  ├─generated
        │  ├─java
        │  │  └─org
        │  │      └─example
        │  └─resources
        └─test
            ├─java
            └─resources

```


<aside>
🔍 참고자료

- https://www.data.go.kr/data/15084084/openapi.do
- https://techblog.woowahan.com/2637/
</aside>
