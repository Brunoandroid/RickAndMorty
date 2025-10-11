# RickAndMorty

App Android (Kotlin) que consome a API pública Rick and Morty para listar personagens e exibir detalhes. Projeto focado em boas práticas, testabilidade e CI/CD com SonarCloud.

## Tecnologias e Arquitetura
- Kotlin • MVVM • Coroutines + Flow
- Hilt (DI) • Navigation Component • Fragments + RecyclerView
- Retrofit + OkHttp • PagingSource customizado
- Testes: JUnit e testes instrumentados (Espresso)

## Requisitos
- JDK 17
- Android SDK API 33 (Build Tools 30.0.3)
- Gradle Wrapper incluso

## Como executar
macOS/Linux:
- Testes: ./gradlew testDebugUnitTest
- Lint: ./gradlew lintDebug
- APK (debug): ./gradlew assembleDebug
- Instrumentados: iniciar emulador e rodar ./gradlew connectedDebugAndroidTest

Windows (PowerShell/CMD):
- Testes: gradlew.bat testDebugUnitTest
- Lint: gradlew.bat lintDebug
- APK (debug): gradlew.bat assembleDebug
- Instrumentados: iniciar emulador e rodar gradlew.bat connectedDebugAndroidTest

## CI/CD e Qualidade
- GitHub Actions: .github/workflows/project-ci.yml
- SonarCloud: https://sonarcloud.io/summary/new_code?id=RickAndMorty

## Estrutura
- app/ — código-fonte, recursos e testes
- sonar-project.properties — configuração SonarCloud
- gradle*, settings.gradle — build via Wrapper
