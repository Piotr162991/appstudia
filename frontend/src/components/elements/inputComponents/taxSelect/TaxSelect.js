import React from 'react';
import {TaxRates} from "../../utils/PredefinedValues";
import "./taxSelect.scss"
import classNames from "classnames";

const TaxSelect = ({
                       onChange,
                       savedValue,
                       disabled,
                       formik,
                       label = "Tax percentage",
                       isRequired = true,
                       className
                   }) => {

    return (
        <div className={classNames("tax-select", className,{
            disabled: disabled,
            error: formik.submitCount > 0 && formik.errors["tax"] && formik.touched["tax"]
        })}>
            <label className={classNames("input-label", {required: isRequired})}>
                {label}
            </label>
            <div className="select-values">
                {TaxRates.map(
                    (tax) => (
                        <label key={tax.value}>
                            <input
                                type="radio"
                                name="tax"
                                value={tax.value}
                                checked={savedValue === tax.value}
                                onChange={(e) => onChange(e.target.value)}
                                disabled={disabled}
                                onBlur={formik.handleBlur}
                            />
                            {tax.label}
                        </label>
                    )
                )}
            </div>
        </div>
    );
};

export default TaxSelect;