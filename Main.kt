import kotlin.math.abs

class PhanSo(private var tu: Int, private var mau: Int) {

    init {
        if (mau == 0) {
            throw IllegalArgumentException("Mẫu số không thể bằng 0")
        }
        rutGon()
    }

    // Phương thức nhập phân số
    fun nhap() {
        var t: Int
        var m: Int
        do {
            print("Nhập tử số: ")
            t = readln().toInt()
            print("Nhập mẫu số: ")
            m = readln().toInt()
            if (m == 0) {
                println("Mẫu số không được bằng 0, nhập lại!")
            }
        } while (m == 0)
        tu = t
        mau = m
        rutGon()
    }

    // Phương thức in phân số
    fun xuat() {
        if (mau == 1) {
            print("$tu")
        } else {
            print("$tu/$mau")
        }
    }

    // Tối giản phân số
    private fun rutGon() {
        val ucln = gcd(abs(tu), abs(mau))
        tu /= ucln
        mau /= ucln
        if (mau < 0) { // để mẫu dương
            tu = -tu
            mau = -mau
        }
    }

    // So sánh phân số
    fun soSanh(ps: PhanSo): Int {
        val left = this.tu.toLong() * ps.mau
        val right = ps.tu.toLong() * this.mau
        return when {
            left < right -> -1
            left > right -> 1
            else -> 0
        }
    }

    // Cộng phân số
    fun cong(ps: PhanSo): PhanSo {
        val newTu = this.tu * ps.mau + ps.tu * this.mau
        val newMau = this.mau * ps.mau
        return PhanSo(newTu, newMau)
    }

    // Hàm hỗ trợ
    private fun gcd(a: Int, b: Int): Int {
        return if (b == 0) a else gcd(b, a % b)
    }
}

// ================== CHƯƠNG TRÌNH CHÍNH ==================
fun main() {
    print("Nhập số lượng phân số: ")
    val n = readln().toInt()
    val arr = Array(n) { PhanSo(1, 1) }

    println("\n=== Nhập mảng phân số ===")
    for (i in 0 until n) {
        println("Phân số thứ ${i + 1}:")
        arr[i] = PhanSo(1, 1)
        arr[i].nhap()
    }

    println("\n=== Mảng phân số vừa nhập ===")
    for (ps in arr) {
        ps.xuat()
        print("  ")
    }
    println()

    println("\n=== Mảng phân số sau khi tối giản ===")
    for (ps in arr) {
        ps.xuat()
        print("  ")
    }
    println()

    println("\n=== Tổng các phân số ===")
    var tong = PhanSo(0, 1)
    for (ps in arr) {
        tong = tong.cong(ps)
    }
    tong.xuat()
    println()

    println("\n=== Phân số lớn nhất ===")
    var maxPS = arr[0]
    for (ps in arr) {
        if (ps.soSanh(maxPS) > 0) {
            maxPS = ps
        }
    }
    maxPS.xuat()
    println()

    println("\n=== Mảng phân số sắp xếp giảm dần ===")
    arr.sortWith { a, b -> b.soSanh(a) } // giảm dần
    for (ps in arr) {
        ps.xuat()
        print("  ")
    }
    println()
}
