import java.util.*

class LoanCalculator {
    var loanAmount: Double = 0.0
        set(value) {
            if (value > 1_000_000) {
                throw IllegalArgumentException("El monto del préstamo no debe ser mayor a $1,000,000")
            }
            field = value
        }

    var annualInterestRate: Double = 0.0
        set(value) {
            if (value > 33.09) {
                throw IllegalArgumentException("La tasa de interés no debe superar el 33.09% anual")
            }
            field = value
        }

    var years: Int = 0
        set(value) {
            if (value > 50) {
                throw IllegalArgumentException("El plazo máximo es de 50 años")
            }
            field = value
        }

    fun calculateMonthlyPayment(): Double {
        val monthlyInterestRate = annualInterestRate / 12 / 100
        val numberOfPayments = years * 12
        val monthlyPayment = (loanAmount * monthlyInterestRate) /
                (1 - Math.pow(1 + monthlyInterestRate, (-numberOfPayments).toDouble()))
        return monthlyPayment
    }
}

fun main() {
    val scanner = Scanner(System.`in`)

    println("Calculadora de préstamos")
    println("Ingrese el monto del préstamo:")
    val loanAmount = scanner.nextDouble()

    println("Ingrese la tasa de interés anual:")
    val annualInterestRate = scanner.nextDouble()

    println("Ingrese el período de amortización en años:")
    val years = scanner.nextInt()

    val loanCalculator = LoanCalculator()
    try {
        loanCalculator.loanAmount = loanAmount
        loanCalculator.annualInterestRate = annualInterestRate
        loanCalculator.years = years

        val monthlyPayment = loanCalculator.calculateMonthlyPayment()
        println("El pago mensual es: $monthlyPayment")
    } catch (e: IllegalArgumentException) {
        println("Error: ${e.message}")
    }
}
