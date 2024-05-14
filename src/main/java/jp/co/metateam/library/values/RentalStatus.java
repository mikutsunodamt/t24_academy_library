package jp.co.metateam.library.values;

import jp.co.metateam.library.model.RentalManage;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RentalStatus implements Values {
    RENT_WAIT(0, "貸出待ち")
    , RENTAlING(1, "貸出中")
    , RETURNED(2, "返却済み")
    , CANCELED(3, "キャンセル");

    private final Integer value;
    private final String text;  
}

    //貸出ステータスの値をとってくる処理
    //for文の中にif文







