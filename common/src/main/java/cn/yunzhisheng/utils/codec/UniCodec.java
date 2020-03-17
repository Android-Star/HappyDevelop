package cn.yunzhisheng.utils.codec;

import android.util.Log;

public class UniCodec {

	static {
		Log.e("load is or not?", "yes");
		System.loadLibrary("unicodec");
	}

	private long handle;
	private boolean mode;
	private int error;

	public int getError() {
		return error;
	}

	public long getHandle() {
		return handle;
	}

	public UniCodec(boolean mode) {
		super();
		this.mode = mode;
		this.error = 0;
		long[] h = new long[1];
		CodecInit(h, mode);
		handle = h[0];
	}

	public byte[] deal(byte[] data) {
		int[] e = new int[1];
		byte[] ret;
		if (mode) {
			ret = CodecCompress(handle, data, e);
		} else {
			ret = CodecExtract(handle, data, e);
		}
		error = e[0];
		return ret;
	}

	public void release() {
		if (handle < 0) {
			CodecFini(handle);
			handle = -1;
			error = 0;
		}
	}

	public static void releaseHandle(UniCodec uc) {
		uc.release();
	}

	private native int CodecInit(long[] handle, boolean option);

	private native byte[] CodecCompress(long handle, byte[] sample, int[] len);

	private native byte[] CodecExtract(long handle, byte[] sample, int[] len);

	private native int CodecFini(long handle);

}
