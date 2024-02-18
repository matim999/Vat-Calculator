package com.example.vatcalculator.utils.textfilters;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;

import java.util.regex.Pattern;

public class DecimalDigitsInputFilter implements InputFilter {
    private final int mDigitsBeforeZero;
    private final int mDigitsAfterZero;
    private Pattern mPattern;

    public DecimalDigitsInputFilter(int integerCount, int decimalCount) {
        this.mDigitsBeforeZero = integerCount;
        this.mDigitsAfterZero = decimalCount;
        this.mPattern = Pattern.compile("-?[0-9]{0," + this.mDigitsBeforeZero + "}+((\\.[0-9]{0," + this.mDigitsAfterZero + "})?)|(\\.)?");
    }

    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        String charSequence2 = charSequence.subSequence(i, i2).toString();
        if (this.mPattern.matcher(spanned.subSequence(0, i3) + charSequence2 + spanned.subSequence(i4, spanned.length())).matches()) {
            return null;
        }
        return TextUtils.isEmpty(charSequence) ? spanned.subSequence(i3, i4) : "";
    }
}