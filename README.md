# RickAndMorty

[![Android CI](https://github.com/Brunoandroid/RickAndMorty/actions/workflows/android-ci.yml/badge.svg)](https://github.com/Brunoandroid/RickAndMorty/actions/workflows/android-ci.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=RickAndMorty&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=RickAndMorty)

Projeto Android (Kotlin) com CI via GitHub Actions.

CI configurado para:
- Executar testes unitários e Android Lint em todo push e PR para `master` e `develop`.
- Executar testes instrumentados (androidTest) somente em PRs para `master` e no build noturno (nightly).

Requisitos do pipeline:
- JDK 17
- Android SDK: API 33 e Build Tools 30.0.3

Como rodar localmente (exemplos):
- Testes unitários: `./gradlew testDebugUnitTest`
- Lint (debug): `./gradlew lintDebug`
- APK debug: `./gradlew assembleDebug`
- Testes instrumentados: iniciar um emulador/disp. físico e rodar `./gradlew connectedDebugAndroidTest`
