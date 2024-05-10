# Diplom_API

Проект по автоматизации тестирования для ресурса [Stellar Burgers](https://stellarburgers.nomoreparties.site/)

> Stellar Burgers - интернет магазин по продаже бургеров (учебный проект для тестирования)  
> Ссылка на [документацию](https://code.s3.yandex.net/qa-automation-engineer/java/cheatsheets/paid-track/diplom/api-documentation.pdf)


### Реализованные проверки:
* Успешное создание пользователя
* Создание пользователя с данными, которые уже есть в системе
* Разные варианты неуспешного создания пользователя
* Успешное удаление пользователя
* Удаление несущестувующего пользователя
* Получение списка ингредиентов

____
### Технологии и инструменты:
<p align="center">
<img src="images/logo/Intelij_IDEA.svg" width="50" height="50"  alt="IDEA"/>
<img src="images/logo/Java.svg" width="50" height="50"  alt="Java"/>
<img src="images/logo/Github.svg" width="50" height="50"  alt="Github"/>
<img src="images/logo/JUnit5.svg" width="50" height="50"  alt="JUnit 5"/>
<img src="images/logo/Gradle.svg" width="50" height="50"  alt="Gradle"/>
<img src="images/logo/Selenide.svg" width="50" height="50"  alt="Selenide"/>
<img src="images/logo/Allure_Report.svg" width="50" height="50"  alt="Allure_Report"/>
<img src="images/logo/Jenkins.svg" width="50" height="50"  alt="Jenkins"/>
</p>


____
<a id="jenkins"></a>
</a><a name="Сборка"></a>Сборка в [Jenkins](https://jenkins.autotests.cloud/job/MalovDiplomUI/)</a>
____

<img src="images/screens/jenkins.png" alt="Jenkins" width="950"/>


<a id="console"></a>
## Команды для запуска из терминала
___
***Локальный запуск:***
```bash  
gradle clean test 
```

#### Удаленный запуск тестов реализован через сборку в jenkins


<a id="allure"></a>
## <img alt="Allure" height="25" src="images/logo/Allure_Report.svg" width="25"/></a> <a name="Allure"></a>Allure [отчет](https://jenkins.autotests.cloud/job/MalovDiplomAPI/5/allure/)</a>
___


<p align="center">  
<img title="Allure Overview Dashboard" src="images/screens/Report.png" width="850">  
</p>  
