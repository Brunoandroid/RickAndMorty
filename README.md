# RickAndMorty

App Android (Kotlin) que consome a [API pública Rick and Morty](https://rickandmortyapi.com/) para listar personagens, e exibir detalhes. Projeto focado em boas práticas, testabilidade e CI/CD com SonarCloud.

![Build](https://github.com/Brunoandroid/RickAndMorty/actions/workflows/project-ci.yml/badge.svg)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=RickAndMorty&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=RickAndMorty)
![Kotlin](https://img.shields.io/badge/Kotlin-1.9.24-7F52FF?logo=kotlin&logoColor=white)
![API](https://img.shields.io/badge/Android%20API-21%2B-brightgreen?logo=android)

## Layout mobile
<div align="center">
  <table>
    <tr>
      <td><img src="https://github.com/Brunoandroid/Imagens/blob/main/futurama_home.gif" width="350" height="700"/></td>
      <td><img src="https://github.com/Brunoandroid/Imagens/blob/main/futurama_details.gif" width="350" height="700"/></td>
    </tr>
    <tr>
      <td align="center"><b>Lista de Personagens</b></td>
      <td align="center"><b>Detalhes</b></td>
    </tr>
  </table>
</div>

---

## Funcionalidades

- Listagem paginada de personagens com scroll infinito
- Detalhes do personagem (status, espécie, gênero, origem, localização)
- Lista de episódios em que o personagem aparece
- Bottom sheet com detalhes do episódio
- Header colapsável com MotionLayout
- Consulta ao Google Gemini AI para obter informações do personagem em um episódio específico
- Suporte a temas claro e escuro
- Layout edge-to-edge com tratamento de insets

---

## Tecnologias e Arquitetura

- **Linguagem:** Kotlin
- **Arquitetura:** MVVM + Repository Pattern
- **Injeção de dependência:** Hilt
- **Navegação:** Navigation Component + Safe Args
- **Paginação:** Paging 3 com PagingSource customizado
- **Rede:** Retrofit + OkHttp (dois clientes: Rick & Morty API e Google Gemini API — para consultar informações do personagem em episódios específicos)
- **Carregamento de imagens:** Glide
- **Reatividade:** Coroutines + Flow
- **UI:** Fragments + RecyclerView + MotionLayout + ViewBinding
- **Testes:** JUnit + Mockito + Truth + Espresso

---

## Estrutura do Projeto

```
app/src/main/java/com/example/rickandmorty/
├── base/               # Classes base (BaseViewModel)
├── data/
│   ├── character/      # API Rick & Morty (CharacterApi)
│   ├── gemini/         # API Google Gemini (GeminiApi)
│   ├── model/          # Modelos de dados (Parcelable)
│   └── repository/     # Repositórios (CharactersRepository, GeminiRepository)
├── di/                 # Módulos Hilt + Qualifiers
├── screen/
│   ├── main/           # MainActivity (edge-to-edge)
│   ├── characters/     # Listagem paginada + MotionLayout
│   └── characterDetails/ # Detalhes + episódios + bottom sheet
├── utils/              # Constants, StatusColorUtil
└── widget/             # CharacterCardView (componente customizado)
```

---

## Requisitos

- JDK 17
- Android SDK API 35 (minSdk 21)
- Gradle Wrapper incluso

---

## Como Executar

**macOS / Linux:**
```bash
# Testes unitários
./gradlew testDebugUnitTest

# Lint
./gradlew lintDebug

# APK debug
./gradlew assembleDebug

# Testes instrumentados (emulador em execução)
./gradlew connectedDebugAndroidTest
```

**Windows (PowerShell / CMD):**
```bat
gradlew.bat testDebugUnitTest
gradlew.bat lintDebug
gradlew.bat assembleDebug
gradlew.bat connectedDebugAndroidTest
```

---

## CI/CD e Qualidade

- **GitHub Actions:** `.github/workflows/project-ci.yml`
- **SonarCloud:** [Painel do projeto](https://sonarcloud.io/summary/new_code?id=RickAndMorty)

