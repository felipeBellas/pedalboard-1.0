# Pedalboard (nome provisório)

Pedalboard de guitarra para Android com foco em **áudio de baixa latência** e **baixo consumo de bateria**.
Deslize para os lados para trocar de pedal; os pedais continuam ligados e configurados. A barra inferior mostra os pedais e acende os ativos.

## Estrutura
- `app/src/main/cpp/dsp.h`: cadeia de efeitos (Overdrive, Tremolo, Delay) em C++, sem alocação nem locks no callback.
- `app/src/main/cpp/engine.cpp`: streams Oboe (entrada e saída, `LowLatency`, `Exclusive`, float) e ponte JNI.
- `app/src/main/assets/ui/`: interface HTML/CSS/JS (scroll-snap nativo para o deslize, sem animação contínua).
- `MainActivity.kt`: WebView mínimo e permissão de microfone.

## Decisões de economia
- Pedal desligado não gasta CPU; tudo desligado vira passagem direta.
- Flush-to-zero (ARM64) contra denormals, `-O3 -ffast-math`, LFO triangular em vez de `sin()`.
- Taxa de amostragem nativa do aparelho (sem resampling), buffer de 2 bursts.
- Áudio parado quando o app sai de cena. UI sem loops de animação.
- Apenas `arm64-v8a`.

## Compilar
Abra a pasta no Android Studio (ele gera o Gradle wrapper) e rode em um aparelho real, com uma interface USB de guitarra class-compliant ou o microfone do aparelho para testar.

## Roadmap
1. Serviço em primeiro plano (áudio com tela desligada) e modo Eco/Performance de buffer.
2. Sobreamostragem 2x no overdrive e cabines por convolução (IR).
3. Modelos neurais pequenos (RTNeural), mais pedais, presets e versão iOS.
4. ADPF (`PerformanceHintManager`) na thread de áudio.
