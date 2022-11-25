const toNumberAndInvert = (values) => {
    return [values[0], ...values.slice(1).reverse().map(value => {
        return [value[0], ...value.slice(1).map(numericValue => {
            return Number(numericValue);
        })]
    })];
}

export {toNumberAndInvert}