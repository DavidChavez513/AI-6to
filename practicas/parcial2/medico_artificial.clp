
; === Sistema Experto para Diagnóstico Médico (Bacteriano o Viral) ===

(deftemplate paciente
  (slot nombre))

(deftemplate sintoma
  (slot paciente)
  (slot nombre))

(deftemplate diagnostico
  (slot paciente)
  (slot enfermedad)
  (slot tipo)) ; viral o bacteriana

(deftemplate receta
  (slot paciente)
  (slot medicamento))

(deftemplate analisis
  (slot paciente)
  (slot prueba))


; === Regla inicial para insertar hechos de ejemplo ===

(defrule iniciar-hechos
  =>
  ; Paciente 1: Juan, síntomas de COVID-19
  (assert (paciente (nombre Juan)))
  (assert (sintoma (paciente Juan) (nombre fiebre)))
  (assert (sintoma (paciente Juan) (nombre tos-seca)))
  (assert (sintoma (paciente Juan) (nombre perdida-olfato)))

  ; Paciente 2: Maria, síntomas de Neumonía
  (assert (paciente (nombre Maria)))
  (assert (sintoma (paciente Maria) (nombre fiebre-alta)))
  (assert (sintoma (paciente Maria) (nombre dificultad-respirar)))

  ; Paciente 3: Pedro, síntomas de Gastroenteritis
  (assert (paciente (nombre Pedro)))
  (assert (sintoma (paciente Pedro) (nombre dolor-abdominal)))
  (assert (sintoma (paciente Pedro) (nombre diarrea)))
  (assert (sintoma (paciente Pedro) (nombre vomito)))
)


; === Reglas de Diagnóstico ===

(defrule diagnosticar-gastroenteritis
  (sintoma (paciente ?p) (nombre dolor-abdominal))
  (sintoma (paciente ?p) (nombre diarrea))
  (sintoma (paciente ?p) (nombre vomito))
  =>
  (assert (diagnostico (paciente ?p) (enfermedad "Gastroenteritis") (tipo viral))))

(defrule diagnosticar-ets
  (sintoma (paciente ?p) (nombre dolor-al-orinar))
  (sintoma (paciente ?p) (nombre secrecion-genital))
  (sintoma (paciente ?p) (nombre ulceras-genitales))
  =>
  (assert (diagnostico (paciente ?p) (enfermedad "Enfermedad de Transmisión Sexual") (tipo bacteriana))))

(defrule diagnosticar-covid
  (sintoma (paciente ?p) (nombre fiebre))
  (sintoma (paciente ?p) (nombre tos-seca))
  (sintoma (paciente ?p) (nombre perdida-olfato))
  =>
  (assert (diagnostico (paciente ?p) (enfermedad "COVID-19") (tipo viral))))

(defrule diagnosticar-neumonia
  (sintoma (paciente ?p) (nombre fiebre-alta))
  (sintoma (paciente ?p) (nombre dificultad-respirar))
  =>
  (assert (diagnostico (paciente ?p) (enfermedad "Neumonía") (tipo bacteriana))))

(defrule diagnosticar-faringitis
  (sintoma (paciente ?p) (nombre dolor-garganta))
  (sintoma (paciente ?p) (nombre fiebre))
  =>
  (assert (diagnostico (paciente ?p) (enfermedad "Faringitis Estreptocócica") (tipo bacteriana))))

(defrule diagnosticar-asma
  (sintoma (paciente ?p) (nombre dificultad-respirar))
  (sintoma (paciente ?p) (nombre silbidos-respirar))
  (sintoma (paciente ?p) (nombre tos-nocturna))
  =>
  (assert (diagnostico (paciente ?p) (enfermedad "Asma") (tipo viral))))

(defrule diagnosticar-bronquitis
  (sintoma (paciente ?p) (nombre tos))
  (sintoma (paciente ?p) (nombre dolor-pecho))
  (sintoma (paciente ?p) (nombre fatiga))
  =>
  (assert (diagnostico (paciente ?p) (enfermedad "Bronquitis") (tipo viral))))
  (defrule diagnosticar-gastritis
    (sintoma (paciente ?p) (nombre dolor-abdominal))
    (sintoma (paciente ?p) (nombre nausea))
    (sintoma (paciente ?p) (nombre acidez))
    =>
    (assert (diagnostico (paciente ?p) (enfermedad "Gastritis") (tipo bacteriana))))


; === Reglas de Recetas y Análisis ===

(defrule receta-gripe
  (diagnostico (paciente ?p) (enfermedad "Gripe"))
  =>
  (assert (receta (paciente ?p) (medicamento "Paracetamol")))
  (assert (receta (paciente ?p) (medicamento "Reposo"))))

(defrule receta-covid
  (diagnostico (paciente ?p) (enfermedad "COVID-19"))
  =>
  (assert (receta (paciente ?p) (medicamento "Acetaminofén")))
  (assert (receta (paciente ?p) (medicamento "Aislamiento y reposo"))))

(defrule receta-neumonia
  (diagnostico (paciente ?p) (enfermedad "Neumonía"))
  =>
  (assert (receta (paciente ?p) (medicamento "Antibióticos")))
  (assert (analisis (paciente ?p) (prueba "Radiografía de tórax"))))

(defrule receta-faringitis
  (diagnostico (paciente ?p) (enfermedad "Faringitis Estreptocócica"))
  =>
  (assert (receta (paciente ?p) (medicamento "Penicilina")))
  (assert (receta (paciente ?p) (medicamento "Ibuprofeno"))))

(defrule receta-bronquitis
  (diagnostico (paciente ?p) (enfermedad "Bronquitis"))
  =>
  (assert (receta (paciente ?p) (medicamento "Jarabe para la tos")))
  (assert (receta (paciente ?p) (medicamento "Reposo")))
  (assert (analisis (paciente ?p) (prueba "Espirometría"))))

  (defrule receta-gastroenteritis
    (diagnostico (paciente ?p) (enfermedad "Gastroenteritis"))
    =>
    (assert (receta (paciente ?p) (medicamento "Suero oral")))
    (assert (receta (paciente ?p) (medicamento "Dieta blanda"))))

  (defrule receta-ets
    (diagnostico (paciente ?p) (enfermedad "Enfermedad de Transmisión Sexual"))
    =>
    (assert (receta (paciente ?p) (medicamento "Antibióticos de amplio espectro")))
    (assert (analisis (paciente ?p) (prueba "Pruebas de laboratorio específicas"))))

  (defrule receta-asma
    (diagnostico (paciente ?p) (enfermedad "Asma"))
    =>
    (assert (receta (paciente ?p) (medicamento "Broncodilatadores")))
    (assert (receta (paciente ?p) (medicamento "Corticoides inhalados"))))

  (defrule receta-gastritis
    (diagnostico (paciente ?p) (enfermedad "Gastritis"))
    =>
    (assert (receta (paciente ?p) (medicamento "Inhibidores de bomba de protones")))
    (assert (receta (paciente ?p) (medicamento "Antiácidos"))))


; === Reglas de impresión del resultado final ===

(defrule imprimir-diagnostico
  (diagnostico (paciente ?p) (enfermedad ?e) (tipo ?t))
  =>
  (printout t "Diagnóstico para " ?p ": " ?e " (" ?t ")" crlf))

(defrule imprimir-receta
  (receta (paciente ?p) (medicamento ?m))
  =>
  (printout t "Receta para " ?p ": Tomar " ?m crlf))

(defrule imprimir-analisis
  (analisis (paciente ?p) (prueba ?a))
  =>
  (printout t "Análisis recomendado para " ?p ": " ?a crlf))

  (defrule imprimir-sin-diagnostico
    (paciente (nombre ?p))
    (not (diagnostico (paciente ?p) (enfermedad ?e) (tipo ?t)))
    =>
    (printout t "No se encontró un diagnóstico para " ?p crlf))
