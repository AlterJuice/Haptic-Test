package com.example.haptictest.ui

import android.content.ClipData
import android.content.Context
import android.os.Build
import android.os.CombinedVibration
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Ease
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseInBack
import androidx.compose.animation.core.EaseInBounce
import androidx.compose.animation.core.EaseInCirc
import androidx.compose.animation.core.EaseInCubic
import androidx.compose.animation.core.EaseInElastic
import androidx.compose.animation.core.EaseInExpo
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.EaseInOutBack
import androidx.compose.animation.core.EaseInOutBounce
import androidx.compose.animation.core.EaseInOutCirc
import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.EaseInOutElastic
import androidx.compose.animation.core.EaseInOutExpo
import androidx.compose.animation.core.EaseInOutQuad
import androidx.compose.animation.core.EaseInOutQuart
import androidx.compose.animation.core.EaseInOutQuint
import androidx.compose.animation.core.EaseInOutSine
import androidx.compose.animation.core.EaseInQuad
import androidx.compose.animation.core.EaseInQuart
import androidx.compose.animation.core.EaseInQuint
import androidx.compose.animation.core.EaseInSine
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.EaseOutBack
import androidx.compose.animation.core.EaseOutBounce
import androidx.compose.animation.core.EaseOutCirc
import androidx.compose.animation.core.EaseOutCubic
import androidx.compose.animation.core.EaseOutElastic
import androidx.compose.animation.core.EaseOutExpo
import androidx.compose.animation.core.EaseOutQuad
import androidx.compose.animation.core.EaseOutQuart
import androidx.compose.animation.core.EaseOutQuint
import androidx.compose.animation.core.EaseOutSine
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.PathData
import androidx.compose.ui.graphics.vector.toPath
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.ClipEntry
import androidx.compose.ui.platform.LocalClipboard
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.round
import kotlin.math.roundToInt

@Composable
fun HapticTestScreen(
    modifier: Modifier
) {
    val haptic = LocalHapticFeedback.current
    Column(
        modifier
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .verticalScroll(
                rememberScrollState()
            )
    ) {
        Button(
            onClick = {
                haptic.performHapticFeedback(
                    HapticFeedbackType.Confirm
                )
            }
        ) {
            Text("Click to Confirm")
        }


        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(16.dp)
                // Use combinedClickable to handle the long press
                .combinedClickable(
                    onClick = {
                        haptic.performHapticFeedback(
                            HapticFeedbackType.Confirm
                        )
                    },
                    onLongClick = {
                        haptic.performHapticFeedback(
                            HapticFeedbackType.ContextClick
                        )
                    }
                ),
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    "Long Press for Context Menu",
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
        ThresholdActivateHapticComponent()

        GestureEndHapticComponent()
        KeyboardTapHapticComponent()
        LongPressHapticComponent()
        RejectHapticComponent()
        SegmentFrequentTickHapticComponent()
        SegmentTickHapticComponent()
        TextHandleMoveHapticComponent()
        ToggleHapticComponent()
        ExpressiveSpringSlider()
        StandardSliderWithSpringBack()
        SpringAnimatedHapticBox()
        WaveformAnimatedHapticBox()

        HapticTunerComponent()
    }
}

private val AllComposeEasings = listOf(
    Ease to "Ease",
    EaseOut to "EaseOut",
    EaseIn to "EaseIn",
    EaseInOut to "EaseInOut",
    EaseInSine to "EaseInSine",
    EaseOutSine to "EaseOutSine",
    EaseInOutSine to "EaseInOutSine",
    EaseInCubic to "EaseInCubic",
    EaseOutCubic to "EaseOutCubic",
    EaseInOutCubic to "EaseInOutCubic",
    EaseInQuint to "EaseInQuint",
    EaseOutQuint to "EaseOutQuint",
    EaseInOutQuint to "EaseInOutQuint",
    EaseInCirc to "EaseInCirc",
    EaseOutCirc to "EaseOutCirc",
    EaseInOutCirc to "EaseInOutCirc",
    EaseInQuad to "EaseInQuad",
    EaseOutQuad to "EaseOutQuad",
    EaseInOutQuad to "EaseInOutQuad",
    EaseInQuart to "EaseInQuart",
    EaseOutQuart to "EaseOutQuart",
    EaseInOutQuart to "EaseInOutQuart",
    EaseInExpo to "EaseInExpo",
    EaseOutExpo to "EaseOutExpo",
    EaseInOutExpo to "EaseInOutExpo",
    EaseInBack to "EaseInBack",
    EaseOutBack to "EaseOutBack",
    EaseInOutBack to "EaseInOutBack",
    EaseInElastic to "EaseInElastic",
    EaseOutElastic to "EaseOutElastic",
    EaseInOutElastic to "EaseInOutElastic",
    EaseOutBounce to "EaseOutBounce",
    EaseInBounce to "EaseInBounce",
    EaseInOutBounce to "EaseInOutBounce",
)

enum class HapticMode(val displayName: String) {
    WAVEFORM_COMPOSITION("Waveform (Плавний)"),
    DISCRETE_ONESHOT("Discrete (Покроковий)")
}


/**
 * Генерує Waveform (Ease-In або Ease-Out) з динамічними параметрами.
 */
fun generateDynamicWaveformEffect(
    isAppearing: Boolean,
    durationMs: Long,
    scaleFactor: Float,
    numPoints: Int, // <--- НОВИЙ ПАРАМЕТР,
    easing: Easing,
): VibrationEffect? {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O || numPoints <= 0) return null

    // Визначаємо інтервал між точками
    val interval = durationMs / numPoints
    val timings = LongArray(numPoints) { interval }
    val amplitudes = IntArray(numPoints)

    for (i in 0 until numPoints) { // Використовуємо numPoints для циклу
        val progress = i.toFloat() / numPoints

        val normalizedStrength = if (isAppearing) {
            easing.transform(progress)
            easeInOutScale(progress)
        } else {
            easing.transform(1f - progress)
        }

        val finalStrength = normalizedStrength * scaleFactor
        val amplitude =
            (finalStrength * MAX_AMPLITUDE_SCALE).roundToInt().coerceIn(0, MAX_AMPLITUDE_SCALE)
        amplitudes[i] = amplitude
    }

    return VibrationEffect.createWaveform(timings, amplitudes, -1)
}

/**
 * Створює ефект різкого фінального скачка з динамічними параметрами.
 */
fun createDynamicJumpEffect(durationMs: Long, scaleFactor: Float): VibrationEffect? {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return null

    // Перетворення масштабу (0-1) на амплітуду (0-255)
    val amplitude =
        (scaleFactor * MAX_AMPLITUDE_SCALE).roundToInt().coerceIn(0, MAX_AMPLITUDE_SCALE)

    return VibrationEffect.createOneShot(durationMs, amplitude)
}

@Composable
fun HapticTunerComponent() {
    val context = LocalContext.current
    val manager = remember {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        } else {
            null
        }
    }
    val snackbarHostState = remember { SnackbarHostState() } // Для повідомлення про успіх

    // --- СТАНИ ДЛЯ НАЛАШТУВАННЯ ---
    var isVisible by remember { mutableStateOf(false) }
    var animationActive by remember { mutableStateOf(false) }

    // 1. Параметри Waveform
    var wfDuration by remember { mutableFloatStateOf(300f) } // 100ms - 500ms
    var wfScale by remember { mutableFloatStateOf(0.4f) } // 0.1 - 1.0
    var wfPoints by remember { mutableFloatStateOf(30f) }

    // 2. Параметри Jump
    var jumpDuration by remember { mutableFloatStateOf(40f) } // 10ms - 100ms
    var jumpScale by remember { mutableFloatStateOf(1.0f) } // 0.1 - 1.0

    var selectedEasing by remember { mutableStateOf(Ease to "Ease") }

    var hapticMode by remember { mutableStateOf(HapticMode.WAVEFORM_COMPOSITION) }

    // --- АНІМАЦІЯ ОБ'ЄКТА ---
    val scale = animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(
            durationMillis = jumpDuration.toInt() + wfDuration.toInt(),
            easing = selectedEasing.first,
        ),
        label = "scale_animation",
        finishedListener = { animationActive = false }
    )

    // --- ЛОГІКА HAPTIC COMPOSITION ---
    LaunchedEffect(isVisible, hapticMode) { // Додаємо hapticMode до ключів
        if (manager != null) {

            if (!manager.defaultVibrator.hasAmplitudeControl()) return@LaunchedEffect

            animationActive = true
            manager.cancel()

            // Параметри
            val duration = wfDuration.toLong()
            val points = wfPoints.roundToInt()

            // --- РЕЖИМ 1: WAVEFORM COMPOSITION (ПЛАВНИЙ, З ПОСЛІДОВНІСТЮ) ---
            if (hapticMode == HapticMode.WAVEFORM_COMPOSITION) {

                val waveform = generateDynamicWaveformEffect(
                    isAppearing = isVisible,
                    durationMs = duration,
                    scaleFactor = wfScale,
                    numPoints = points,
                    easing = selectedEasing.first
                )
                val jump = createDynamicJumpEffect(
                    durationMs = jumpDuration.toLong(),
                    scaleFactor = jumpScale
                )

                if (waveform != null) {
                    manager.vibrate(CombinedVibration.createParallel(waveform))
                    // Створення послідовної композиції, що виправляє проблему Jump
                    // Jump додаємо лише при появі (якщо він існує)
                    delay(duration)
                    if (isVisible && jump != null) {
                        manager.defaultVibrator.vibrate(jump)
                        delay(jumpDuration.toLong())
                    }
                }
                // Чекаємо завершення композиції
            }

            // --- РЕЖИМ 2: DISCRETE ONESHOT (ПОКРОКОВИЙ) ---
            else if (hapticMode == HapticMode.DISCRETE_ONESHOT) {

                val interval = duration / points
                val easing = selectedEasing

                for (i in 0 until points) {
                    val progress = i.toFloat() / points

                    val scaledProgress = if (isVisible) {
                        easing.first.transform(progress)
                    } else {
                        easing.first.transform(1f - progress)
                    }

                    // Створення та відтворення одного тіка (без CombinedVibration)
                    val finalStrength = scaledProgress * wfScale
                    val amplitude = (finalStrength * MAX_AMPLITUDE_SCALE).roundToInt()
                        .coerceIn(0, MAX_AMPLITUDE_SCALE)

                    if (amplitude > 0) {
                        manager.defaultVibrator.vibrate(
                            VibrationEffect.createOneShot(10L, amplitude)
                        )
                    }

                    delay(interval - 10)
                }

                // Фінальний Jump для дискретного режиму (якщо активна поява)
                if (isVisible) {
                    val jump = createDynamicJumpEffect(
                        durationMs = jumpDuration.toLong(), scaleFactor = jumpScale
                    )
                    if (jump != null) manager.defaultVibrator.vibrate(jump)
                }
            }

            manager.cancel()
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (manager == null) {
            Text(
                "⚠️ Потрібен Android 11+ для VibratorManager. Haptic-ефекти будуть відсутні.",
                color = MaterialTheme.colorScheme.error
            )
        }

        // --- БЛОК НАЛАШТУВАННЯ ---
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp)
            ) {

                Text("Налаштування Easing:", style = MaterialTheme.typography.titleMedium)
//
//                FlowRow(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(top = 8.dp),
//                    horizontalArrangement = Arrangement.spacedBy(8.dp),
//                    verticalArrangement = Arrangement.spacedBy(8.dp),
//                ) {
//                    AllComposeEasings.forEach { (easingOption, name) ->
//                        FilterChip(
//                            selected = name == selectedEasing.second,
//                            onClick = {
//                                // Оновлюємо Flow при виборі
//                                selectedEasing = easingOption to name
//                            },
//                            label = { Text(name) }
//                        )
//                    }
//                }

                var expanded by remember { mutableStateOf(false) }
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expanded = true }
                        .padding(top = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Display the curve of the CURRENTLY SELECTED Easing next to the label
                        EasingCurveDisplay(easing = selectedEasing.first)
                        Spacer(Modifier.width(8.dp))
                        Text("Крива Waveform (Easing): ${selectedEasing.second}")
                        Spacer(Modifier.weight(1f))
                    }
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    AllComposeEasings.forEach { (easingOption, name) ->
                        DropdownMenuItem(
                            text = {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    // Draw the curve IN THE DROPDOWN MENU ITEM
                                    EasingCurveDisplay(easing = easingOption)
                                    Spacer(Modifier.width(8.dp))
                                    Text(name)
                                }
                            },
                            onClick = {
                                selectedEasing = easingOption to name
                                expanded = false
                            }
                        )
                    }
                }
                Divider(Modifier.padding(vertical = 8.dp))

                Text(
                    "Поточний Easing: ${selectedEasing.second}",
                    style = MaterialTheme.typography.titleMedium
                )

                var modeExpanded by remember { mutableStateOf(false) }

                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { modeExpanded = true }
                        .padding(top = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Режим Haptic: ${hapticMode.displayName}")
                        Spacer(Modifier.weight(1f))
                    }
                }

                DropdownMenu(
                    expanded = modeExpanded,
                    onDismissRequest = { modeExpanded = false }
                ) {
                    HapticMode.entries.forEach { mode ->
                        DropdownMenuItem(
                            text = { Text(mode.displayName) },
                            onClick = {
                                hapticMode = mode
                                modeExpanded = false
                            }
                        )
                    }
                }


                Text("Налаштування Haptic Композиції", style = MaterialTheme.typography.titleMedium)
                Divider(Modifier.padding(vertical = 8.dp))


                // Waveform Duration
                Text(
                    "Waveform Тривалість: ${wfDuration.roundToInt()} мс",
                    style = MaterialTheme.typography.bodySmall
                )
                Slider(
                    value = wfDuration,
                    onValueChange = { wfDuration = it },
                    valueRange = 100f..1000f
                )

                // Waveform Scale
                Text(
                    "Waveform Сила (0-1): ${"%.2f".format(wfScale)}",
                    style = MaterialTheme.typography.bodySmall
                )
                Slider(value = wfScale, onValueChange = { wfScale = it }, valueRange = 0.01f..1.0f)

                // Waveform Points (НОВИЙ СЛАЙДЕР)
                Text(
                    "Waveform Точки (Плавність): ${wfPoints.roundToInt()}",
                    style = MaterialTheme.typography.bodySmall
                )
                // Рекомендований діапазон: від 10 (крок) до 60 (дуже плавно)
                Slider(
                    value = wfPoints,
                    onValueChange = { wfPoints = it },
                    valueRange = 1f..60f,
                    steps = 60 // Щоб зробити перехід менш плаваючим
                )

                // Jump Duration
                Text(
                    "End Hit Тривалість: ${jumpDuration.roundToInt()} мс",
                    style = MaterialTheme.typography.bodySmall
                )
                Slider(
                    value = jumpDuration,
                    onValueChange = { jumpDuration = it },
                    valueRange = 1f..100f
                )

                // Jump Scale
                Text(
                    "End Hit Сила (0-1): ${"%.2f".format(jumpScale)}",
                    style = MaterialTheme.typography.bodySmall
                )
                Slider(
                    value = jumpScale,
                    onValueChange = { jumpScale = it },
                    valueRange = 0.00f..1.0f
                )
            }
        }

        val clipboardManager = LocalClipboard.current

        val scope = rememberCoroutineScope()

        // --- ФУНКЦІЯ КОПІЮВАННЯ ---
        val copyParametersToClipboard: () -> Unit = {

            // 1. Форматування Easing Name
            val easingName = selectedEasing.second

            // 2. Форматування Ha
            val message = """
// --- Haptic Parameters (Tuned) ---
HAPTIC_MODE = HapticMode.${hapticMode.name} // ${hapticMode.displayName}
WF_DURATION = ${wfDuration.roundToInt()}L
WF_SCALE = ${"%.2f".format(wfScale)}f
WF_POINTS = ${wfPoints.roundToInt()}
JUMP_DURATION = ${jumpDuration.roundToInt()}L
JUMP_SCALE = ${"%.2f".format(jumpScale)}f
val EASING_FUNCTION = $${selectedEasing.second}
"""

            scope.launch {
                clipboardManager.setClipEntry(
                    ClipEntry(
                        ClipData.newPlainText(
                            "Haptic параметри",
                            message
                        )
                    )
                )
                snackbarHostState.showSnackbar(
                    "Параметри скопійовано до буфера обміну!",
                    duration = SnackbarDuration.Short
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {



            // ... (Повідомлення про VibratorManager, БЛОК НАЛАШТУВАННЯ) ...

            // --- КНОПКА КОПІЮВАННЯ ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { isVisible = !isVisible },
                    enabled = manager != null,
                ) {
                    Text(if (isVisible) "Сховати / Тест" else "Показати / Тест")
                }

                TextButton(
                    onClick = copyParametersToClipboard,
                    enabled = manager != null
                ) {

                    Text("Копіювати Налаштування")
                }
            }
            Spacer(Modifier.height(30.dp))


            FlowRow(
horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .graphicsLayer {
                            scaleX = scale.value
                            scaleY = scale.value
                            alpha = scale.value
                        }
                        .background(
                            MaterialTheme.colorScheme.secondaryContainer,
                            RoundedCornerShape(12.dp)
                        )
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Тестовий Об'єкт",
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .graphicsLayer {
                            rotationZ = 90f * scale.value
                        }
                        .background(
                            MaterialTheme.colorScheme.secondaryContainer,
                            RoundedCornerShape(12.dp)
                        )
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Тестовий Об'єкт",
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
    SnackbarHost(hostState = snackbarHostState) // Хост для повідомлень
}


// Фіксована тривалість для Waveform (зменшена, щоб дати місце для Jump)
    const val FINAL_JUMP_AMPLITUDE = VibrationEffect.DEFAULT_AMPLITUDE // Максимальна сила
    const val FINAL_JUMP_DURATION = 40L // Короткий, різкий скачок

    /**
     * Генерує Waveform (Ease-In або Ease-Out) і повертає його як VibrationEffect.
     * Більше не відтворює вібрацію сам.
     */
    fun generateSmoothWaveformEffect(
        isAppearing: Boolean,
        scaleFactor: Float = 0.35f
    ): VibrationEffect? {
        // Перевірка, щоб уникнути помилок, якщо викликано на старих API
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return null

        val timings = LongArray(NUM_WAVEFORM_POINTS) { WAVEFORM_INTERVAL }
        val amplitudes = IntArray(NUM_WAVEFORM_POINTS)

        for (i in 0 until NUM_WAVEFORM_POINTS) {
            val progress = i.toFloat() / NUM_WAVEFORM_POINTS

            val normalizedStrength = if (isAppearing) {
                easeInOutScale(progress) // Ease-In: наростання
            } else {
                easeInOutScale(1f - progress) // Ease-Out: затухання
            }

            val finalStrength = normalizedStrength * scaleFactor
            val amplitude =
                (finalStrength * MAX_AMPLITUDE_SCALE).roundToInt().coerceIn(0, MAX_AMPLITUDE_SCALE)
            amplitudes[i] = amplitude
        }

        return VibrationEffect.createWaveform(timings, amplitudes, -1)
    }

    /**
     * Створює ефект різкого фінального скачка.
     */
    fun createFinalJumpEffect(): VibrationEffect? {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return null

        // Дуже короткий і сильний імпульс для чіткого завершення
        return VibrationEffect.createOneShot(FINAL_JUMP_DURATION, FINAL_JUMP_AMPLITUDE)
    }


    const val WAVEFORM_DURATION_MS = 300L
    const val NUM_WAVEFORM_POINTS = 30 // Кількість кроків для плавності
    const val WAVEFORM_INTERVAL = WAVEFORM_DURATION_MS / NUM_WAVEFORM_POINTS

// Змінюємо константи для досягнення "гладкості"
    const val SMOOTH_STRENGTH_MULTIPLIER =
        0.35f // Трохи збільшимо загальну силу, оскільки імпульси дуже короткі

    // Функція для створення Waveform на основі заданої функції масштабування
    fun generateSmoothWaveform(
        isAppearing: Boolean,
        vibrator: Vibrator,
        scaleFactor: Float = 0.35f
    ) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O || !vibrator.hasAmplitudeControl()) return

        val timings = LongArray(NUM_WAVEFORM_POINTS) { WAVEFORM_INTERVAL }
        val amplitudes = IntArray(NUM_WAVEFORM_POINTS)

        for (i in 0 until NUM_WAVEFORM_POINTS) {
            // Прогрес від 0.0f до 1.0f
            val progress = i.toFloat() / NUM_WAVEFORM_POINTS

            // Визначаємо силу залежно від фази:
            val normalizedStrength = if (isAppearing) {
                // Ease-In (наростання)
                EaseInElastic.transform(progress)
            } else {
                EaseInOut.transform(1f - progress)
            }

            val finalStrength = normalizedStrength * scaleFactor
            val amplitude =
                (finalStrength * MAX_AMPLITUDE_SCALE).roundToInt().coerceIn(0, MAX_AMPLITUDE_SCALE)

            amplitudes[i] = amplitude
        }

        // Створюємо та відтворюємо ЄДИНИЙ Waveform
        val effect = VibrationEffect.createWaveform(timings, amplitudes, -1) // -1 = без повтору
        vibrator.vibrate(effect)
    }

    @Composable
    fun EasingCurveDisplay(
        easing: Easing,
        modifier: Modifier = Modifier.size(60.dp, 30.dp),
        curveColor: Color = Color.Blue,
        backgroundColor: Color = Color.LightGray.copy(alpha = 0.5f)
    ) {


        Box(
            modifier = modifier
                .background(backgroundColor) // Фон застосовуємо як звичайний модифікатор
                .drawWithCache {
                    val width = size.width
                    val height = size.height

                    val path = PathData {
                        moveTo(0f, height)
                        for (i in 0..100) {
                            val fraction = i / 100f

                            // 1. Застосовуємо Easing
                            val easedValue = easing.transform(fraction)

                            // 2. Обчислення координат
                            val x = fraction * width
                            // Y-координата інвертована (y=height для easedValue=0)
                            val y = height * (1f - easedValue)

                            lineTo(x, y)
                        }
                    }.toPath()


                    // Команда малювання, що використовує обчислений Path
                    onDrawBehind {
                        drawPath(
                            path = path,
                            color = curveColor,
                            style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
                        )
                    }
                }
        )
    }

    @Composable
    fun WaveformAnimatedHapticBox() {
        val context = LocalContext.current
        val vibrator = remember { context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator }

        var isVisible by remember { mutableStateOf(false) }
        var animationActive by remember { mutableStateOf(false) }

        // Анімація масштабу
        val scale = animateFloatAsState(
            targetValue = if (isVisible) 1f else 0f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessMediumLow
            ),
            label = "scale_animation",
            // Обробник, що викликається після завершення анімації
            finishedListener = { animationActive = false }
        )

        // LaunchedEffect для СИНХРОНІЗАЦІЇ Haptics (Waveform)
        LaunchedEffect(isVisible) {
            // Запускаємо логіку лише при зміні видимості
            if (isVisible != animationActive) {
                animationActive = true

                // 1. Зупиняємо будь-яку поточну вібрацію
                vibrator.cancel()

                // 2. ГЕНЕРАЦІЯ ТА ВІДТВОРЕННЯ ЄДИНОГО WAVEFORM:
                generateSmoothWaveform(
                    isAppearing = isVisible,
                    vibrator = vibrator,
                    scaleFactor = SMOOTH_STRENGTH_MULTIPLIER // Наша "мікро" сила
                )

                // 3. Чекаємо, поки анімація завершиться (чи Waveform)
                // Ми додаємо невелику затримку для компенсації часу вібрації, якщо spring-анімація швидша
                delay(WAVEFORM_DURATION_MS)

                // 4. Скасовуємо вібрацію на випадок, якщо вона ще працює
                vibrator.cancel()
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(onClick = { isVisible = !isVisible }) {
                Text(if (isVisible) "Сховати (Waveform Ease-Out)" else "Показати (Waveform Ease-In)")
            }

            Spacer(Modifier.height(30.dp))

            Box(
                modifier = Modifier
                    .size(200.dp)
                    .graphicsLayer {
                        scaleX = scale.value
                        scaleY = scale.value
                        alpha = scale.value
                    }
                    .background(
                        MaterialTheme.colorScheme.primaryContainer,
                        RoundedCornerShape(12.dp)
                    )
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Waveform Анімований Компонент",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }

// Константи залишаються тими ж, але ми зменшуємо загальну силу для "мікровібрації"
    const val MAX_AMPLITUDE_SCALE = 255
    const val HAPTIC_DURATION_MS = 10L // Дуже короткий тік
    const val FINAL_STRENGTH_MULTIPLIER = 0.20f // Дуже низька сила

    // Функції масштабування (використовуємо їх залежно від фази анімації)
    fun easeInOutScale(value: Float): Float {
        return EaseInOut.transform(value)
        // Проста парабола для симетричного ефекту (сила найбільша в середині анімації)
        // return 1f - (1f - value).pow(2)
    }

    @Composable
    fun SpringAnimatedHapticBox() {
        val context = LocalContext.current
        val vibrator = remember { context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator }

        // Стан, що контролює, чи повинен компонент бути "показаним"
        var isVisible by remember { mutableStateOf(false) }

        // Анімація масштабу (від 0.0f до 1.0f)
        val scale = animateFloatAsState(
            targetValue = if (isVisible) 1f else 0f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioLowBouncy, // Low Bouncy
                stiffness = Spring.StiffnessMediumLow
            ),
            label = "scale_animation",

            )

        // Функція для запуску мікровібрації
        val playCustomHaptic: (Float) -> Unit = { normalizedStrength ->
            val finalStrength = min(1f, normalizedStrength) * FINAL_STRENGTH_MULTIPLIER
            val amplitude =
                (finalStrength * MAX_AMPLITUDE_SCALE).roundToInt().coerceIn(0, MAX_AMPLITUDE_SCALE)

            if (amplitude > 0 && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && vibrator.hasAmplitudeControl()) {
                val effect = VibrationEffect.createOneShot(HAPTIC_DURATION_MS, amplitude)
                vibrator.vibrate(effect)
            }
        }

        // LaunchedEffect для синхронізації Haptics з анімацією
        LaunchedEffect(scale.value) {
            // scale.value знаходиться між 0f і 1f під час анімації

            // 1. Нормалізована позиція, як відсоток завершеності анімації
            val animProgress = scale.value

            // 2. Визначення сили haptic за допомогою функції масштабування
            // Використовуємо Ease-In/Out, щоб сила була найбільшою, коли компонент найшвидше рухається.
            val hapticStrength = easeInOutScale(animProgress)

            // Граємо haptic імпульс
            // Перевіряємо, чи анімація активна і не знаходиться біля кінцевих точок
            if (hapticStrength > 0.05f) {
                playCustomHaptic(hapticStrength)
                // Затримка, щоб haptic не спрацьовував занадто часто і давав відчуття "вібраційних тіків"
                delay(5)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(onClick = { isVisible = !isVisible }) {
                Text(if (isVisible) "Сховати (Fade-Out Haptics)" else "Показати (Fade-In Haptics)")
            }

            Spacer(Modifier.height(30.dp))

            // Візуалізація компонента
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .graphicsLayer {
                        // 1. Анімація масштабу
                        scaleX = scale.value
                        scaleY = scale.value
                        // 2. Анімація прозорості (щоб повністю зникнути, коли scale = 0)
                        alpha = scale.value
                    }
                    .background(
                        MaterialTheme.colorScheme.tertiaryContainer,
                        RoundedCornerShape(12.dp)
                    )
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Анімований Компонент",
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(Modifier.height(20.dp))
            Text(
                text = "Haptic-сила масштабується відповідно до швидкості Spring-анімації. Найсильніший haptic буде в середині анімації.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }


    /**
     * Функція Ease-In (наростання) для сили під час активного перетягування.
     * Сила зростає повільно на початку і швидко в кінці.
     */
    fun easeInScale(value: Float): Float {
        return value.pow(3)
    }

    /**
     * Функція Ease-Out (затухання) для сили під час анімації повернення до нуля.
     * Сила є високою на початку анімації (коли повзунок далеко від 0) і плавно затухає.
     */
    fun easeOutScale(value: Float): Float {
        // Швидкий старт, повільне затухання до 0
        return 1f - (1f - value).pow(2)
    }


    @Composable
    fun StandardSliderWithSpringBack() {
        val context = LocalContext.current
        val vibrator = remember { context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator }
        val density = LocalDensity.current
        // Animatable контролює фактичну візуальну позицію слайдера
        val sliderAnimatable = remember { Animatable(0f) }

        // Стан для відстеження, чи користувач АКТИВНО перетягує повзунок
        var isDragging by remember { mutableStateOf(false) }


        // Максимальна ширина треку повзунка
        val trackWidthDp = 300.dp
        // Перетворення ширини в пікселі для Animatable
        val trackWidthPx = with(density) { trackWidthDp.toPx() }

        // Animatable керує горизонтальною позицією повзунка (від 0f до trackWidthPx)
        val offsetX = remember { Animatable(0f) }

        // Поточна нормалізована позиція (від 0.0f до 1.0f)
        val normalizedPosition = (offsetX.value / trackWidthPx).coerceIn(0f, 1f)


        // Функція для запуску haptic імпульсу з динамічною силою
        val playCustomHaptic: (Float) -> Unit = { normalizedStrength ->
            // Застосовуємо загальне зменшення сили в 4 рази
            val finalStrength = min(70f, normalizedStrength) * FINAL_STRENGTH_MULTIPLIER
            val amplitude =
                (finalStrength * MAX_AMPLITUDE_SCALE).roundToInt().coerceIn(0, MAX_AMPLITUDE_SCALE)

            // Перевірка підтримки амплітуди
            if (amplitude > 0 && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && vibrator.hasAmplitudeControl()) {
                val effect = VibrationEffect.createOneShot(HAPTIC_DURATION_MS, amplitude)
                vibrator.vibrate(effect)
            }
        }

        // Цей LaunchedEffect працює як "onDragEnd"
        LaunchedEffect(isDragging) {
            // Якщо isDragging щойно змінився на false
            if (!isDragging) {
                // Запускаємо анімацію повернення до нуля
                launch {
                    sliderAnimatable.animateTo(
                        targetValue = 0f,
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    )
                }
            }
        }

        // LaunchedEffect для Haptics під час Ease-Out (як у попередньому прикладі)
        LaunchedEffect(sliderAnimatable.value) {
            if (!isDragging && sliderAnimatable.isRunning) {
                val normalizedPosition = sliderAnimatable.value
                if (normalizedPosition > 0.01f) {
                    // Граємо затухаючий імпульс (Ease-Out)
                    val decayStrength = easeOutScale(normalizedPosition)
                    playCustomHaptic(decayStrength)
                    delay(HAPTIC_DURATION_MS)
                }
            }
        }
        val scope = rememberCoroutineScope()

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Позиція: ${"%.2f".format(sliderAnimatable.value)}",
                style = MaterialTheme.typography.titleMedium
            )

            // Використовуємо стандартний Slider
            Slider(
                value = sliderAnimatable.value, // Візуалізуємо позицію Animatable
                onValueChange = { newValue ->
                    // 1. Зупиняємо анімацію, якщо вона працює, і позначаємо як перетягування
                    scope.launch { sliderAnimatable.stop() }
                    isDragging = true

                    // 2. Оновлюємо позицію повзунка відповідно до руки користувача
                    scope.launch { sliderAnimatable.snapTo(newValue) }

                    // 3. Логіка Haptics Ease-In під час перетягування
                    if (newValue > 0.01f) {
                        val dragStrength = easeInScale(newValue)
                        playCustomHaptic(dragStrength)
                    }
                },
                // Цей колбек викликається, коли палець користувача відпускає повзунок
                onValueChangeFinished = {
                    // Ми отримали "onDragEnd"!
                    isDragging = false // Це запустить LaunchedEffect вище
                },
                valueRange = 0f..1f,
                steps = 0
            )

            Spacer(Modifier.height(20.dp))
            Text(
                text = "Використовується стандартний Slider. Haptic Ease-In працює під час перетягування. Haptic Ease-Out та Spring-Back анімація запускаються після відпускання.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ExpressiveSpringSlider() {
        // 1. Setup: State and Haptic Access
        val context = LocalContext.current
        val vibrator = remember { context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator }

        // The raw position being dragged by the user (0f to 1f)
        val dragPosition = remember { mutableFloatStateOf(0f) }

        // The animated position that follows the drag, using a spring
        val animatedPosition = animateFloatAsState(
            targetValue = dragPosition.floatValue,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            ),
            label = "slider_spring_animation"
        )

        // Helper function to play a custom haptic
        val playCustomHaptic: (Float) -> Unit = { strength ->
            // Map 0f-1f strength to a 0-255 amplitude scale
            val amplitude = (strength * 255).roundToInt().coerceIn(0, 255)

            // Only trigger if we have a non-zero amplitude
            if (amplitude > 0) {
                val effect = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    // Use a short, low-frequency vibration (e.g., 20ms)
                    VibrationEffect.createOneShot(20, amplitude)
                } else {
                    // Deprecated, but handles older APIs
                    @Suppress("DEPRECATION")
                    VibrationEffect.createOneShot(20, amplitude)
                }
                vibrator.vibrate(effect)
            }
        }

        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Slider Value: ${"%.2f".format(dragPosition.floatValue)}",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(8.dp))

            Slider(
                value = animatedPosition.value, // Use the animated value for visual position
                onValueChange = { newValue ->
                    dragPosition.floatValue = newValue

                    // 2. Dynamic Haptics: Calculate strength based on proximity to 1.0f
                    val distanceFromEnd = 1.0f - newValue

                    // Strength should be HIGH when distance is LOW.
                    // We'll use 1.0f - distanceFromEnd (which is just newValue),
                    // but scale it non-linearly (e.g., quadratic) to make the boost near the end more dramatic.
                    val strength =
                        min(1f, newValue * newValue) // Strength increases quadratically from 0 to 1

                    playCustomHaptic(strength * 0.5f) // Scale down for a 'softer' feel
                },
                valueRange = 0f..1f,
                steps = 0, // Continuous slider
                thumb = {
                    // Custom thumb to clearly show the spring animation
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(MaterialTheme.colorScheme.primary, CircleShape)
                            .graphicsLayer {
                                // Scale the thumb up slightly based on proximity to 1.0f for visual reinforcement
                                val scaleFactor = 1f + (animatedPosition.value * 0.2f)
                                scaleX = scaleFactor
                                scaleY = scaleFactor
                            }
                    )
                }
            )
            Text(
                "Haptic strength scales with the value, peaking near the end (1.0). Visual jump uses spring.",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        // Optional: Trigger a final, strong 'Confirm' haptic when the spring settles at the endpoint (1.0f)
        LaunchedEffect(animatedPosition.value) {
            if (abs(animatedPosition.value - 1.0f) < 0.001f) {
                // Trigger a strong, final haptic for completion
                vibrator.vibrate(
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE)
                    } else {
                        @Suppress("DEPRECATION")
                        VibrationEffect.createOneShot(50, 255)
                    }
                )
            }
        }
    }

    @Composable
    fun ToggleHapticComponent() {
        val haptic = LocalHapticFeedback.current
        val isToggled =
            remember { mutableStateOf(true) } // Start in the 'On' state for better demonstration

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(8.dp))
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Feature Status: ${if (isToggled.value) "ON" else "OFF"}",
                style = MaterialTheme.typography.titleMedium
            )

            Switch(
                checked = isToggled.value,
                onCheckedChange = { newState ->
                    // Check the direction of the change
                    if (newState) {
                        // Changing from OFF to ON
                        haptic.performHapticFeedback(
                            HapticFeedbackType.ToggleOn
                        )
                    } else {
                        // Changing from ON to OFF
                        haptic.performHapticFeedback(
                            HapticFeedbackType.ToggleOff
                        )
                    }
                    isToggled.value = newState
                }
            )
        }
    }

    @Composable
    fun TextHandleMoveHapticComponent() {
        val haptic = LocalHapticFeedback.current
        val textState = remember { mutableStateOf("Start typing here...") }

        Column(modifier = Modifier.padding(16.dp)) {
            OutlinedTextField(
                value = textState.value,
                onValueChange = { newValue ->
                    // Check if the cursor position/selection changed,
                    // which often implies handle movement in a real scenario
                    if (newValue.length != textState.value.length) {
                        // Trigger the TextHandleMove haptic feedback
                        // This is an approximation of the actual handle move event.
                        haptic.performHapticFeedback(
                            HapticFeedbackType.TextHandleMove
                        )
                    }
                    textState.value = newValue
                },
                label = { Text("Text Input") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Add a button to explicitly test the haptic as well
            Button(
                onClick = {
                    haptic.performHapticFeedback(
                        HapticFeedbackType.TextHandleMove
                    )
                }
            ) {
                Text("Simulate Handle Drag (Button)")
            }
            Text(
                "The haptic should fire when the text/cursor position changes (simulating the handle moving).",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }

    @Composable
    fun SegmentTickHapticComponent() {
        val haptic = LocalHapticFeedback.current
        val sliderPosition = remember { mutableFloatStateOf(0f) }
        // Low number of steps (e.g., 5) for distinct segments
        val stepCount = 5
        val tickResolution = 1f / (stepCount + 1) // Each step is 1/6th of the range

        // Track the last value that triggered a haptic to avoid repeating on drag
        val lastHapticValue = remember { mutableFloatStateOf(0f) }

        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Step: ${(sliderPosition.floatValue * stepCount).toInt()}",
                style = MaterialTheme.typography.titleSmall
            )
            Slider(
                value = sliderPosition.floatValue,
                onValueChange = { newValue ->
                    sliderPosition.floatValue = newValue

                    // Calculate the nearest discrete step value
                    val steppedValue = round(newValue * stepCount) / stepCount

                    if (abs(steppedValue - lastHapticValue.floatValue) > 0.001f) {
                        // Trigger the standard SegmentTick haptic feedback
                        haptic.performHapticFeedback(
                            HapticFeedbackType.SegmentTick
                        )
                        lastHapticValue.floatValue = steppedValue
                    }
                },
                steps = stepCount,
                valueRange = 0f..1f
            )
            Text(
                "Move the slider to feel distinct ticks for each segment.",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }

    @Composable
    fun SegmentFrequentTickHapticComponent() {
        val haptic = LocalHapticFeedback.current
        val sliderPosition = remember { mutableFloatStateOf(0f) }
        // A step size that encourages frequent haptic feedback
        val stepCount = 50
        val tickResolution = 1f / stepCount // Each step is 0.02

        // Track the last value that triggered a haptic to avoid repeating
        val lastHapticValue = remember { mutableFloatStateOf(0f) }

        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Value: ${"%.2f".format(sliderPosition.floatValue)}",
                style = MaterialTheme.typography.titleSmall
            )
            Slider(
                value = sliderPosition.floatValue,
                onValueChange = { newValue ->
                    sliderPosition.floatValue = newValue

                    // Check if the new value has crossed a step boundary
                    val stepValue = (newValue / tickResolution).toInt() * tickResolution

                    if (stepValue != lastHapticValue.floatValue) {
                        // Trigger the SegmentFrequentTick haptic feedback
                        haptic.performHapticFeedback(
                            HapticFeedbackType.SegmentFrequentTick
                        )
                        lastHapticValue.floatValue = stepValue
                    }
                },
                steps = stepCount,
                valueRange = 0f..1f
            )
            Text(
                "Move the slider to feel frequent, delicate ticks.",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }

    @Composable
    fun RejectHapticComponent() {
        val haptic = LocalHapticFeedback.current
        val validationFailed = remember { mutableStateOf(false) }

        Button(
            onClick = {
                // Simulate a validation failure
                validationFailed.value = true

                // Trigger the Reject haptic feedback
                haptic.performHapticFeedback(
                    HapticFeedbackType.Reject
                )
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (validationFailed.value) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.padding(16.dp)
        ) {
            Text(if (validationFailed.value) "Invalid Input - Rejected!" else "Submit and Fail")
        }

        // Reset the state after a short delay for visualization
        LaunchedEffect(validationFailed.value) {
            if (validationFailed.value) {
                delay(1000)
                validationFailed.value = false
            }
        }
    }

    @Composable
    fun LongPressHapticComponent() {
        val haptic = LocalHapticFeedback.current

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(16.dp)
                .combinedClickable(
                    onClick = {
                        // Short click is ignored in this example
                    },
                    onLongClick = {
                        haptic.performHapticFeedback(
                            HapticFeedbackType.LongPress
                        )
                    }
                ),
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colorScheme.secondaryContainer
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    "Long Press Me",
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }
    }

    @Composable
    fun KeyboardTapHapticComponent() {
        val haptic = LocalHapticFeedback.current

        // NOTE: For full compatibility across all devices and Android versions,
        // some developers find using LocalView and HapticFeedbackConstants
        // works better for system constants like KEYBOARD_TAP.
        // If HapticFeedbackType.KeyboardTap doesn't work, this is the fallback:
        // val view = LocalView.current

        Button(
            onClick = {
                haptic.performHapticFeedback(
                    HapticFeedbackType.KeyboardTap
                )
                // Fallback alternative:
                // view.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
            },
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(4.dp)),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text("Key", style = MaterialTheme.typography.titleLarge)
        }
    }

    @Composable
    fun ThresholdActivateHapticComponent() {
        val haptic = LocalHapticFeedback.current
        val dragDistance = remember { mutableStateOf(0f) }
        val threshold = 500f // Define a threshold distance in pixels
        val thresholdReached = remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(8.dp))
                .padding(16.dp)
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragStart = {
                            thresholdReached.value = false // Reset on drag start
                        },
                        onDrag = { change, dragAmount ->
                            change.consume()
                            dragDistance.value += dragAmount.y

                            // Check if the drag crossed the threshold and hasn't been triggered yet
                            if (abs(dragDistance.value) > threshold && !thresholdReached.value) {
                                haptic.performHapticFeedback(
                                    HapticFeedbackType.GestureThresholdActivate
                                )
                                thresholdReached.value = true // Prevent repeated haptic feedback
                            }
                        },
                        onDragEnd = {
                            dragDistance.value = 0f
                        }
                    )
                },
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Drag Down Past Threshold", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(4.dp))
                Text(
                    text = if (thresholdReached.value) "Threshold Activated!" else "Drag distance: ${
                        "%.1f".format(
                            dragDistance.value
                        )
                    }",
                    color = if (thresholdReached.value) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }

    @Composable
    fun GestureEndHapticComponent() {
        val haptic = LocalHapticFeedback.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(MaterialTheme.colorScheme.tertiaryContainer, RoundedCornerShape(8.dp))
                .padding(16.dp)
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDrag = { change, _ ->
                            change.consume()
                            // Optional: Move a UI element here during drag
                        },
                        onDragEnd = {
                            // Trigger the GestureEnd haptic feedback when the finger is lifted
                            haptic.performHapticFeedback(
                                HapticFeedbackType.GestureEnd
                            )
                        }
                    )
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                "Drag and Lift Finger to End Gesture",
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
        }
    }

    @Composable
    @Preview
    private fun HapticTestScreenPreview() {
        HapticTestScreen(
            modifier = Modifier
        )
    }