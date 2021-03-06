
## 模型图
![](mvp1.webp)


## 数据流向图 - The following demonstrates an example data flow throw the MVP.
![](mvp2.webp)
> In this formulation, when a user triggers an event method of the view,
it does nothing but invoke a method of the presenter that has no parameters and no return value.
The presenter then retrieves data from the view through methods defined by the view interface. Finally,
the presenter operates on the model and updates the view with the results of the operation.
https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter

## 参考资料
- [Android Architecture with MVP or MVVM - Tutorial](http://www.vogella.com/tutorials/AndroidArchitecture/article.html)
- [googlesamples/android-architecture](https://github.com/googlesamples/android-architecture/tree/todo-mvp)
- [Android官方MVP架构示例项目解析](http://www.infoq.com/cn/articles/android-official-mvp-architecture-sample-project-analysis)
- [浅谈 MVP in Android - Hongyang - 博客频道 - CSDN.NET](http://blog.csdn.net/lmj623565791/article/details/46596109)
