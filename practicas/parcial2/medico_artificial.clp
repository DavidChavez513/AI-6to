
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
  (assert (paciente (nombre Juan)))
  (assert (sintoma (paciente Juan) (nombre fiebre)))
  (assert (sintoma (paciente Juan) (nombre tos))))

; === El paciente será diagnosticado con gripe ===


; === Reglas de Diagnóstico ===

(defrule diagnosticar-gripe
  (sintoma (paciente ?p) (nombre fiebre))
  (sintoma (paciente ?p) (nombre tos))
  =>
  (assert (diagnostico (paciente ?p) (enfermedad "Gripe") (tipo viral))))

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

(defrule diagnosticar-bronquitis
  (sintoma (paciente ?p) (nombre tos))
  (sintoma (paciente ?p) (nombre dolor-pecho))
  (sintoma (paciente ?p) (nombre fatiga))
  =>
  (assert (diagnostico (paciente ?p) (enfermedad "Bronquitis") (tipo viral))))


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
