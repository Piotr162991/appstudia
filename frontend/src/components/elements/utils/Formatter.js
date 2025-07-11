import {parse} from "date-fns/parse";
import {isValid} from "date-fns";

export const formatPostalCode = (postalCode = "") => {
    let digitsOnly = postalCode.replace(/[^0-9]/g, "").slice(0, 5);
    if (digitsOnly.length > 2) {
        return digitsOnly.slice(0, 2) + '-' + digitsOnly.slice(2);
    }
    return digitsOnly;
};
export const formatPhone = (phone = "") => {
    let trimmed = phone.trim();
    let countryCode = "";
    let digitsOnly = "";
    if (trimmed.startsWith("+")) {
        const match = trimmed.match(/^\+\d{0,2}/);
        if (match) {
            countryCode = match[0];
            const remaining = trimmed.slice(countryCode.length);
            digitsOnly = remaining.replace(/[^0-9]/g, "").slice(0, 9);
        }
    } else {
        digitsOnly = trimmed.replace(/[^0-9]/g, "").slice(0, 9);
    }
    return countryCode + digitsOnly;
}
export const formatNip = (nip = "") => {
    let digitsOnly = nip.replace(/[^0-9]/g, "").slice(0, 10);
    return digitsOnly;
}
export const formatPesel = (pesel = "") => {
    let digitsOnly = pesel.replace(/[^0-9]/g, '').slice(0, 11);
    return digitsOnly;
}
export const formatOnlyNumbers = (input = "") => {
    return String(input).replace(/[a-zA-Z]/g, "");
};
export const handleRentChange = (e, formik) => {
    let value = e.target.value;
    if (!/^\d*\.?\d{0,2}$/.test(value)) {
        return;
    }
    formik.setFieldValue("rent", value);
};

export const handleRentBlur = (formik) => {
    let value = formik.values.rent;
    if (value) {
        value = parseFloat(value).toFixed(2);
        formik.setFieldValue("rent", value);
    }
};

export const formatEstateAddress = (estate) => {
    const street = estate.street || "";
    const buildingNumber = estate.buildingNumber;
    const apartmentNumber = estate.apartmentNumber ? `/${estate.apartmentNumber}` : "";
    const city = estate.city;
    const postalCode = estate.postalCode;

    return `${street} ${buildingNumber}${apartmentNumber}, ${city} ${postalCode}`
}

export const parseDate = (dateStr) => {
    const parsedDate = parse(dateStr, 'dd-MM-yyyy', new Date());
    return isValid(parsedDate) ? parsedDate : null;
};