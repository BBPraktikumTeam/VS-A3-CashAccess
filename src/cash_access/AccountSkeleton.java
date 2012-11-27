package cash_access;

import mware_lib.Communicator;
import mware_lib.Skeleton;

public final class AccountSkeleton extends Thread implements Skeleton {

	private final String name;
	private final Account account;

	public AccountSkeleton(String name, Account account) {
		this.name = name;
		this.account = account;
	}

	@Override
	public void unmarshal(String msg, Communicator comm) {
		String[] resultLine = msg.split(",");
		long msgId = Long.parseLong(resultLine[1]);
		String method = resultLine[2];
		String param = resultLine[3];
		if (method.equals("deposit")) {
			new DepositCaller(comm, msgId, account, param).start();
		} else if (method.equals("getBalance")) {
			new GetBalanceCaller(comm, msgId, account).start();
		} else if (method.equals("withdraw")) {
			new WithdrawCaller(comm,msgId,account,param).start();
		} else{
			// TODO: Error handling
		}
	}

	@Override
	public String name() {
		return name;
	}
}
