**Entregables Sprint #1.**

*Diagramas de dominio:*

-Diagrama de clases: https://drive.google.com/file/d/1mBYR9x0CedSD2n81OPxxPf1kXooMa64Z/view?usp=sharing
-Diagrama de de procesos (bpmn): https://drive.google.com/file/d/1ko9w9y5tzJR1H4Cy5jSaK7w11bxcf55M/view?usp=sharing

*Definición del estilo arquitectónico:*

Al realizar un estudio exhaustivo de cada una de las arquitecturas y sus características específicas, decidimos implementar para este proyecto una arquitectura limpia, ya que es ampliamente utilizada para separar el negocio de la tecnología. Lo interesante de esta arquitectura es que se puede cambiar de tecnología fácilmente sin romper todo, como pasaría si implementáramos la arquitectura por capas, porque es más rígida.

Lo que estamos haciendo es una app de gestión financiera personal, lo que implica tener en cuenta reglas de negocio como ingresos, gastos y balances. Además, como lo desarrollamos por medio de iteraciones, la arquitectura debe ser escalable y abierta a posibles cambios futuros, por ejemplo en la base de datos (que inicialmente se hizo en local con H2).

Se utilizó arquitectura limpia para separar la lógica del negocio de la infraestructura, permitiendo que el sistema sea más mantenible, escalable y adaptable a cambios tecnológicos, algo especialmente importante considerando que estamos simulando una aplicación financiera donde las reglas del negocio son críticas.


*Proyecto base en Spring Boot:*

-Back: https://github.com/isabellasanchezmejia11-11/MiCuentaBE
-Front (opcional): https://github.com/isabellasanchezmejia11-11/MiCuenta_frontend-main

*HU que implementamos:*

| HU | Nombre | SP | Descripción |
|----|--------|-----|-------------|
| HU-01 | Registro de usuario | 3 | Crear cuenta con nombre, correo y contraseña |
| HU-03 | Inicio de sesión | 3 | Login con correo y contraseña |
| HU-08 | Registrar ingreso | 3 | Registrar ingresos con monto, fecha, categoría |
| HU-09 | Registrar gasto | 5 | Registrar gastos con alertas de presupuesto |
| HU-10 | Listar movimientos | 3 | Ver historial de movimientos con filtros |
| HU-18 | Dashboard financiero | 3 | Resumen con stats y gráfica de evolución |

---

**NOTA**

Aclaramos que el código inicial del proyecto se encuentra en la rama **"Integración-inicial-del-proyecto"**, en la cual cada integrante del equipo realizó el respectivo aporte de su parte.

Por otro lado, en la rama **"Integración-final-y-definitiva-del-proyecto"** se encuentra el código final, resultado de la integración completa de todos los aportes en una única versión consolidada.

Código inicial: Integración-inicial-del-proyecto
Código final: Integración-final-y-definitiva-del-proyecto
