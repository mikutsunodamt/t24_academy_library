package jp.co.metateam.library.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.print.CancelablePrintJob;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jp.co.metateam.library.values.RentalStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;



/**
 * 貸出管理DTO
 */
@Getter
@Setter

public class RentalManageDto {

    private Long id;

    @NotEmpty(message="在庫管理番号は必須です")
    private String stockId;

    @NotEmpty(message="社員番号は必須です")
    private String employeeId;

    @NotNull(message="貸出ステータスは必須です")
    private Integer status;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @NotNull(message="貸出予定日は必須です")
    private Date expectedRentalOn;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @NotNull(message="返却予定日は必須です")
    private Date expectedReturnOn;

    private Timestamp rentaledAt;

    private Timestamp returnedAt;

    private Timestamp canceledAt;

    private Stock stock;

    private Account account;

    //日付妥当性（返却予定日は貸出予定日より遅く）
    @AssertTrue(message = "返却予定日は貸出予定日より後の日付を入力してください")
    public boolean isDateValid(){
        if(expectedReturnOn == null || expectedRentalOn == null){
            return true;
        }
        return expectedReturnOn.after(expectedRentalOn);
    }

    
    
    //貸出ステータスのバリデーション

    public String validateStatus (Integer prevStatus){
        if(prevStatus == RentalStatus.RENT_WAIT.getValue() && this.status == RentalStatus.RETURNED.getValue()){
            return("貸出ステータスは貸出待ちから返却済みにできません");
        }else if (prevStatus == RentalStatus.RENTAlING.getValue() && this.status == RentalStatus.RENT_WAIT.getValue()){
            return("貸出ステータスは貸出中から貸出待ちにできません");
        }else if (prevStatus == RentalStatus.RENTAlING.getValue() && this.status == RentalStatus.CANCELED.getValue()){
            return("貸出ステータスは貸出中からキャンセルにできません");
        }else if (prevStatus == RentalStatus.RETURNED.getValue() && this.status == RentalStatus.RENT_WAIT.getValue()){
            return("貸出ステータスは返却済みから貸出待ちにできません");
        }else if (prevStatus == RentalStatus.RETURNED.getValue() && this.status == RentalStatus.RENTAlING.getValue()){
            return("貸出ステータスは返却済みから貸出中にできません");
        }else if (prevStatus == RentalStatus.RETURNED.getValue() && this.status == RentalStatus.CANCELED.getValue()){
            return("貸出ステータスは返却済みからキャンセルにできません");
        }else if (prevStatus == RentalStatus.CANCELED.getValue() && this.status == RentalStatus.RENT_WAIT.getValue()){
            return("貸出ステータスはキャンセルから貸出待ちにできません");
        }else if (prevStatus == RentalStatus.CANCELED.getValue() && this.status == RentalStatus.RENTAlING.getValue()){
            return("貸出ステータスはキャンセルから貸出中にできません");
        }else if (prevStatus == RentalStatus.CANCELED.getValue() && this.status == RentalStatus.RETURNED.getValue()){
            return("貸出ステータスはキャンセルから返却済みにできません");
        }

        return null;







        
    }






}



