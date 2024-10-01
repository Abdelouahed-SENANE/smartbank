

export function calcMonthly(amount  , months) {
    let i = 0.10 / 12;
    let a = amount * i;
    let b = 1 - Math.pow(1 + i , -months)
    const monthly = a / b;
    return monthly;
}
export function calcMonths(amount , monthly ) {
    let i = 0.10 / 12;
    let a = Math.log(monthly / (monthly - (amount * i)))
    let b = Math.log(1 + i);
    const months = Math.fround(a / b)
    return months;
}
