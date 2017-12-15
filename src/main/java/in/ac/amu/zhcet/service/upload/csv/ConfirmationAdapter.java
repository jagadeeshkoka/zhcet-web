package in.ac.amu.zhcet.service.upload.csv;

import in.ac.amu.zhcet.data.model.base.Meta;

import java.util.function.Function;

public class ConfirmationAdapter<T, U extends Meta> {
    private final Confirmation<U> confirmation = new Confirmation<>();
    private final UploadResult<T> uploadResult;
    private Function<T, U> converter;
    private Function<U, String> mapper;

    ConfirmationAdapter(UploadResult<T> uploadResult) {
        this.uploadResult = uploadResult;
    }

    public ConfirmationAdapter<T, U> convert(Function<T, U> converter) {
        this.converter = converter;
        return this;
    }

    public ConfirmationAdapter<T, U> map(Function<U, String> mapper) {
        this.mapper = mapper;
        return this;
    }

    public Confirmation<U> get() {
        if (mapper == null)
            throw new IllegalStateException("Mapper cannot be null");

        uploadResult
                .getUploads()
                .stream()
                .map(converter)
                .forEach(item -> {
                    item.setMeta(mapper.apply(item));
                    confirmation.getData().add(item);
                });

        return confirmation;
    }

}