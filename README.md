
1. Spring Starter Project 생성

	- Name : MosaicWeb
	- Type : Gradle(Buildship)
	- Packaging : war
	- Group : com.hybrid
	- Artifact : MosaicWeb
	- Package : com.hybrid
	
	- Dependencies : web 선택

2. 형상관리
	- Remote Repository 생성(github)
	- Local Repository 생성
		- >> git status
		- >> git log
		- >> dir /A > .gitignore
		- >> notepad .gitignore 편집
		- >> git init
		- >> git add *
		- >> git commit -m "first commit"
	- Local ==> Remote push(최초 동기화)
		- >> git remote -v
		- >> git remote remove origin
		- >> git remote add origin https://github.com/webprotols/MosaicWeb.git
		- >> git push -u origin master

	
	- 개발자 2가 Clone 수행
		- >> git clone https://github.com/webprotols/MosaicWeb.git
		- >> git clone https://github.com/webprotols/MosaicWeb.git MosaicWebDev
		- >> Eclipse Gradle Import
	
3. HTML 설정
	- 설정 불필요
	- mkdir src/main/webapp 폴더 생성
	- notepad src/main/webapp/Hello.html
	- gradle bootRun
	- Firefox ==> http://localhost:8080/Hello.html
	
4. JSP 설정
compile group: 'org.apache.tomcat.embed', name: 'tomcat-embed-jasper', version: '8.5.4'

	- mvnrepository.com >> search : tomcat jasper
	- build.gradle dependency : 
		compile group: 'org.apache.tomcat.embed', name: 'tomcat-embed-jasper', version: '8.5.4' 
	- notepad src/main/webapp/Hello.jsp
	- gradle bootRun
	- Firefox ==> http://localhost:8080/Hello.jsp		
	
	
	
	
	
	
	
	
	
	
			
	
	
	
	
	
	
	
	